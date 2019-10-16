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

public class DataFeedTwitter extends DataFeedJSON {

    // Public APIs : Social : https://github.com/public-apis/public-apis#social
    // Twitter API docs : https://developer.twitter.com/en/docs
    // URL Example : https://api.twitter.com/1.1/search/tweets.json?q=from%3Atwitterdev&result_type=mixed&count=2

    public DataFeedTwitter() {
        super("https://api.twitter.com/1.1/search/tweets.json", "/statuses");

    }

    public void setSearchTerm(String value) {
        addParameter("q", value);
    }

    public void setGeoCode(String value) {
        addParameter("geocode", value);
    }

    public void setLanguage(String value) {
        addParameter("lang", value);
    }

    public void setLocale(String value) {
        addParameter("locale", value);
    }

    public void setResultType(String value) {
        addParameter("result_type", value);
    }

    public void setCount(String value) {
        addParameter("count", value);
    }

    public void setUntil(String value) {
        addParameter("until", value);
    }

    public void setSinceID(String value) {
        addParameter("since_id", value);
    }

    public static void main(String[] args) {

        String apiKey = System.getProperty("datafeed.apikey.twitter");
        if ( apiKey == null ) {
            //System.out.println("Set API Key with -Ddatafeed.apikey.twitter=KEY");
            //System.exit(0);
        }

        DataFeedTwitter dataFeed;
        Table dataTable;

        dataFeed = new DataFeedTwitter();
        dataFeed.setSearchTerm("bitcoin");

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
