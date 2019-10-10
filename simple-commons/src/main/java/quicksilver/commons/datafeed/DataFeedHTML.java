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

import java.io.IOException;
import java.io.StringReader;
import tech.tablesaw.io.Source;
import tech.tablesaw.io.html.HtmlReadOptions;
import tech.tablesaw.io.html.HtmlReader;

public class DataFeedHTML extends DataFeed {

    /**
     *
     * @param baseURLString The URL to read the table from. If a numeric HTML
     * anchor is used, this will select which table to read when there are
     * multiple tables on that page
     */
    public DataFeedHTML(String baseURLString) {
        super(baseURLString);
    }

    @Override
    protected void buildDataSet() {
        try {
            String rurl = buildRequestURL();
            int anchorIndex = rurl.indexOf("#");

            Integer tableIndex = null;

            if (anchorIndex != -1) {
                //see if there's an index in there
                String anchor = rurl.substring(anchorIndex + 1);
                try {
                    tableIndex = Integer.parseInt(anchor);
                } catch (NumberFormatException nfe) {
                    //ignore
                }
            }
            HtmlReadOptions.Builder b = HtmlReadOptions.builder(new Source(new StringReader(dataPayload)));
            if (tableIndex != null) {
                //available after 0.36.1 see https://github.com/jtablesaw/tablesaw/pull/682
//                b.tableIndex(tableIndex);
            }
            HtmlReadOptions o = b.build();
            dataTable = new HtmlReader().read(o);
        } catch (IOException ex) {
            //ignore? it's all in RAM
        }
    }

}
