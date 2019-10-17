/*
 * Copyright 2018-2019 Niels Gron and Contributors All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package quicksilver.commons.datafeed;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import tech.tablesaw.api.DateTimeColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;

public class DataFeedRSS extends DataFeed {

    public DataFeedRSS(String baseURLString) {
        super(baseURLString);
    }

    @Override
    protected void buildDataSet() {
        try {
            SyndFeed feed = new SyndFeedInput().build(new InputStreamReader(new ByteArrayInputStream(dataPayload), charset));
            List<SyndEntry> entries = feed.getEntries();

            Table t = Table.create("RSS");

            //create minimal list of columns since most are optional in RSS
            Map<String, Function<SyndEntry, Object>> columns = new HashMap<>();
            for (SyndEntry e : entries) {
                if (e.getAuthor() != null) {
                    columns.put("author", SyndEntry::getAuthor);
                }
                if (e.getCategories() != null && !e.getCategories().isEmpty()) {
                    columns.put("categories", (entry) -> {
                        String categories = null;
                        if (entry.getCategories() != null && !entry.getCategories().isEmpty()) {
                            categories = String.join(",", entry.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList()));
                        }
                        return categories;
                    });
                }
                if (e.getComments() != null) {
                    columns.put("comments", SyndEntry::getComments);
                }
                if (e.getDescription() != null) {
                    columns.put("description", (entry) -> {
                        return entry.getDescription() != null ? entry.getDescription().getValue() : null;
                    });
                }
                if (e.getTitle() != null) {
                    columns.put("title", SyndEntry::getTitle);
                }
                if (e.getLink() != null) {
                    columns.put("link", SyndEntry::getLink);
                }
                if (e.getPublishedDate() != null) {
                    columns.put("pubDate", (entry) -> {
                        LocalDateTime pubDate = null;
                        if (entry.getPublishedDate() != null) {
                            pubDate = entry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        }
                        return pubDate;
                    });
                }
            }
            t.addColumns(
                    columns.keySet().stream()
                            .map(name -> {
                                if ("pubDate".equals(name)) {
                                    return DateTimeColumn.create(name);
                                } else {
                                    return StringColumn.create(name);
                                }
                            })
                            .toArray(Column[]::new));

            for (SyndEntry e : entries) {
                // author?, category*, comments?, (description|title), link?, pubDate?
                //TODO: source?, enclosure, guid?
                columns.forEach((columnName, getter) -> {
                    t.column(columnName).appendObj(getter.apply(e));
                });
            }

            dataTable = t;
        } catch (IllegalArgumentException | FeedException ex) {
            //TODO: log?
            ex.printStackTrace();
        }
    }

}
