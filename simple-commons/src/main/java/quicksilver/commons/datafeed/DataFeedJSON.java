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

import tech.tablesaw.api.Table;

public class DataFeedJSON extends DataFeed {

    public DataFeedJSON(String baseURLString) {
        super(baseURLString);
    }

    @Override
    protected void buildDataSet() {

        // TODO : create the object for dataTable using the member dataPayload

        try {
            //dataTable = Table.read().csv("../data/file.csv");
            //tech.tablesaw.io.json.JsonReader reader = new tech.tablesaw.io.json.JsonReader();
            //dataTable = reader.read("?Source");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

}
