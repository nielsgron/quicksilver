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

package quicksilver.commons.datafeed.impl;

import quicksilver.commons.datafeed.DataFeedJSON;
import tech.tablesaw.api.Table;

import java.io.IOException;

public class DataFeedNewsAPI extends DataFeedJSON {

    // Public APIs : News : https://github.com/public-apis/public-apis#news
    // NewsAPI : https://newsapi.org/
    // URL Example : https://newsapi.org/v2/everything?q=bitcoin&from=2019-09-11&sortBy=publishedAt&apiKey=API_KEY
    // URL Example : https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=API_KEY
    // URL Example : https://newsapi.org/v2/everything?q=apple&from=2019-10-10&to=2019-10-10&sortBy=popularity&apiKey=API_KEY
    // URL Example : https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=API_KEY
    // URL Example : https://newsapi.org/v2/everything?domains=wsj.com&apiKey=API_KEY

    public static String SECTION_EVERYTHING = "everything";
    public static String SECTION_TOPHEADLINES = "top-headlines";

    public DataFeedNewsAPI(String section) {
        super("https://newsapi.org/v2/" + section, "/articles");
    }

    public void setSearchTerm(String value) {
        addParameter("q", value);
    }

    public void setFromDate(String value) {
        addParameter("from", value);
    }

    public void setToDate(String value) {
        addParameter("to", value);
    }

    public void setCountry(String value) {
        addParameter("country", value);
    }

    public void setCategory(String value) {
        addParameter("category", value);
    }

    public void setSources(String value) {
        addParameter("sources", value);
    }

    public void setDomains(String value) {
        addParameter("domains", value);
    }

    public void setSortBy(String value) {  // publishedAt, popularity
        addParameter("sortBy", value);
    }

    public void setAPIKey(String value) {
        addParameter("apiKey", value);
    }

    public static void main(String[] args) {

        String apiKey = System.getProperty("datafeed.apikey.newsapi");
        if ( apiKey == null ) {
            System.out.println("Set API Key with -Ddatafeed.apikey.newsapi=KEY");
            System.exit(0);
        }

        DataFeedNewsAPI dataFeed;
        Table dataTable;

        dataFeed = new DataFeedNewsAPI(SECTION_EVERYTHING);
        dataFeed.setDomains("wsj.com");
        dataFeed.setAPIKey(apiKey);

        System.out.println("API Requesting URL : " + dataFeed.buildRequest());

        try {
            dataFeed.request();
            dataTable = dataFeed.getDataTable();
            System.out.print(dataTable.structure());
            System.out.print(dataTable.print());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
