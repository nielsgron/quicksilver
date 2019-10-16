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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import tech.tablesaw.api.Table;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class DataFeed {

    // Members for URL building
    private String baseURLString;
    private Map<String, String> parameters = new TreeMap<String, String>();

    // Members for DataSet returned
    protected Table dataTable;
    // Members for Data Payload
    protected byte[] dataPayload;
    
    protected Charset charset = StandardCharsets.UTF_8;

    public DataFeed(String baseURLString) {
        this.baseURLString = baseURLString;
    }

    public void setBaseURLString(String value) {
        this.baseURLString = value;
    }
    
    public void setCharset(Charset c) {
        charset = c;
    }

    protected abstract void buildDataSet() throws IOException;

    protected URI buildRequest() {
        try {
            String query = parameters.entrySet()
                    .stream()
                    .map(e
                            -> e.getKey() + "=" + SpringUtil.encodeUriComponent(e.getValue(), StandardCharsets.UTF_8, SpringUtil.Type.QUERY_PARAM))
                    .collect(Collectors.joining("&"));
            if (!query.isEmpty()) {
                query = "?" + query;
            }

            return new URI(baseURLString + query);
        } catch (URISyntaxException use) {
            throw new IllegalArgumentException(use);
        }
    }

    public void request() throws IOException {
        request(new OkHttpRequester());
    }

    public void request(AbstractHttpRequester req) throws IOException {
        URI uri = buildRequest();

        dataPayload = req.requestURL(uri.toURL());
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
