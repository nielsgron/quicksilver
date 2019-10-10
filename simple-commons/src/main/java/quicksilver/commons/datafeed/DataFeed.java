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
import tech.tablesaw.api.Table;

import java.net.URL;
import java.util.HashMap;

public abstract class DataFeed extends HttpRequester {

    // Members for URL building
    private String baseURLString;
    private HashMap<String, String> parameters = new HashMap<String, String>();

    // Members for DataSet returned
    protected Table dataTable;
    // Members for Data Payload
    protected String dataPayload;

    public DataFeed(String baseURLString) {
        this.baseURLString = baseURLString;
    }

    protected abstract void buildDataSet();

    protected String buildRequestURL() {

        StringBuilder urlStringBuilder = new StringBuilder();

        urlStringBuilder.append(baseURLString);
        // TODO: Need to also add parameters seperated by &

        return urlStringBuilder.toString();
    }

    public void request() throws IOException {

        URL url;
        String urlString = buildRequestURL();

        try {
            url = new java.net.URL(urlString);
        } catch ( Exception e ) {
            url = null;
        }

        dataPayload = requestURLToMemory(url);

        buildDataSet();

    }

    public void addParameter(String key, String value) {

    }

    public Table getDataTable() {
        return dataTable;
    }

}
