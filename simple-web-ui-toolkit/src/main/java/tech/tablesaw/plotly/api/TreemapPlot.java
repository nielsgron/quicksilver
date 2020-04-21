/*
 * Copyright 2019, 2020 Niels Gron and Contributors All Rights Reserved.
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
package tech.tablesaw.plotly.api;

import java.util.Map;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Config;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.event.EventHandler;
import tech.tablesaw.plotly.traces.TreemapTrace;

public class TreemapPlot {

    public static Figure create(String title, String[] ids, Object[] labels, Object[] labelParents) {
        return create(Layout.builder(title).build(), ids, labels, labelParents, null);
    }

    public static Figure create(Layout layout, String[] ids, Object[] labels, Object[] labelParents, Map<String, Object[]> extra) {
        return create(layout, (Config) null, ids, labels, labelParents, extra, new EventHandler[0]);
    }

    public static Figure create(Layout layout, Config config, Table table, String idColumn, String labelsColumn, String parentsColumn, Map<String, Object[]> extra, EventHandler[] handlers) {
        return create(layout,
                config,
                table.stringColumn(idColumn).asObjectArray(),
                table.stringColumn(labelsColumn).asObjectArray(),
                table.stringColumn(parentsColumn).asObjectArray(),
                extra,
                handlers);
    }

    public static Figure create(Layout layout, Config config, String[] ids, Object[] labels, Object[] labelParents, Map<String, Object[]> extra,
            EventHandler[] handlers) {
        TreemapTrace trace = TreemapTrace.builder(
                ids,
                labels,
                labelParents,
                extra)
                .build();
        Figure.FigureBuilder builder = Figure.builder()
                .layout(layout)
                .config(config)
                .addTraces(trace)
                .addEventHandlers(handlers);
        return builder.build();
    }

}
