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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import tech.tablesaw.io.Source;
import tech.tablesaw.io.json.JsonReadOptions;
import tech.tablesaw.io.json.JsonReader;

public class DataFeedJSON extends DataFeed {

    private final String jsonPath;

    public DataFeedJSON(String baseURLString) {
        this(baseURLString, "");
    }

    public DataFeedJSON(String baseURLString, String jsonPath) {
        super(baseURLString);
        this.jsonPath = jsonPath;
    }

    @Override
    protected void buildDataSet() throws IOException {
        JsonReadOptions.Builder builder = JsonReadOptions.builder(new Source(new ByteArrayInputStream(dataPayload), charset));
        if (!jsonPath.isEmpty()) {
            builder = applyPath(builder, jsonPath);
        }
        dataTable = new JsonReader().read(builder.build());
    }

    private JsonReadOptions.Builder applyPath(JsonReadOptions.Builder builder, String jsonPath) throws IOException {
        //only in Tablesaw 0.36.1+, see https://github.com/jtablesaw/tablesaw/pull/684
        // builder = builder.path(jsonPath);
        //... otherwise try reflection:
        try {
            Method m = JsonReadOptions.Builder.class.getDeclaredMethod("path", String.class);
            builder = (JsonReadOptions.Builder) m.invoke(builder, jsonPath);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            //parse JSON, extract the subtree here and serialize it for tablesaw
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tree = mapper.readTree(builder.build().source().inputStream());

            tree = tree.at(jsonPath);
            String newText = mapper.writeValueAsString(tree);

            builder = JsonReadOptions.builder(new Source(new StringReader(newText)));
        }
        return builder;
    }

}
