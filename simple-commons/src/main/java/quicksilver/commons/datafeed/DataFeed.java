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
import java.util.Map;

public abstract class DataFeed extends OkHttpRequester {

    // Members for URL building
    private String baseURLString;
    private HashMap<String, String> parameters = new HashMap<String, String>();

    // Members for DataSet returned
    protected Table dataTable;
    // Members for Data Payload
    protected byte[] dataPayload;

    public DataFeed(String baseURLString) {
        this.baseURLString = baseURLString;
    }

    public void setBaseURLString(String value) {
        this.baseURLString = value;
    }

    protected abstract void buildDataSet();

    protected String buildRequestURL() {

        StringBuilder urlStringBuilder = new StringBuilder();

        urlStringBuilder.append(baseURLString);

        if ( parameters.size() > 0 ) {
            int paramCount = 0;

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                paramCount++;
                if ( paramCount == 1 ) {
                    urlStringBuilder.append("?");
                } else {
                    urlStringBuilder.append("&");
                }
                urlStringBuilder.append(entry.getKey());
                urlStringBuilder.append("=");
                // TODO : Do we need to URL encode this value? Does Apache Commons have a utility method?
                urlStringBuilder.append(entry.getValue());
            }
        }

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

        dataPayload = requestURL(url);
        dataPayload = transformPayload(dataPayload);

        buildDataSet();

    }

    protected byte[] transformPayload(byte[] text) {
        return text;
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public Table getDataTable() {
        return dataTable;
    }

}
