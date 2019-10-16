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
import java.util.Map;
import java.util.TreeMap;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class DataFeed {

    // Members for URL building
    private String baseURLString;
    private Map<String, String> parameters = new TreeMap<String, String>();

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

    protected abstract void buildDataSet() throws IOException;

    protected String buildRequestURL() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseURLString);
        parameters.forEach((key, value) -> builder.queryParam(key, value));
        builder.encode();
        return builder.build().toUriString();
    }

    public void request() throws IOException {
        request(new OkHttpRequester());
    }

    public void request(AbstractHttpRequester req) throws IOException {
        URL url;
        String urlString = buildRequestURL();

        try {
            url = new java.net.URL(urlString);
        } catch ( Exception e ) {
            url = null;
        }

        dataPayload = req.requestURL(url);
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
