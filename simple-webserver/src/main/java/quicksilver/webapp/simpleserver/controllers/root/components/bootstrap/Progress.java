/*
 * Copyright 2018 Niels Gron and Contributors All Rights Reserved.
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

package quicksilver.webapp.simpleserver.controllers.root.components.bootstrap;

import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSProgress;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;

public class Progress extends AbstractComponentsBootstrapPage {

    public Progress() {
        getSideBar().setActiveItem("Progress");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new BSText("<br>"));
        panel.add(new BSText("List of Progress Components"));
        panel.add(new BSText("<br>"));
        panel.add(new BSText("<br>"));

        panel.add(new BSProgress(0));
        //XXX: The demo has a margin-top on progress
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(25));
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(50));
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(75));
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(100));
        panel.add(new BSText("<br>"));

        panel.add(new BSText("<h1>Labels</h1>"));
        panel.add(new BSProgress(25, "25%"));
        panel.add(new BSText("<br>"));

        panel.add(new BSText("<h1>Backgrounds</h1>"));
        panel.add(new BSProgress(25).background(BSProgress.Background.success));
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(50).background(BSProgress.Background.info));
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(75).background(BSProgress.Background.warning));
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(100).background(BSProgress.Background.danger));
        panel.add(new BSText("<br>"));

        //TODO: multiple bars?

        panel.add(new BSText("<h1>Striped</h1>"));
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(10).striped());
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(25).background(BSProgress.Background.success).striped());
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(50).background(BSProgress.Background.info).striped());
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(75).background(BSProgress.Background.warning).striped());
        panel.add(new BSText("<br>"));
        panel.add(new BSProgress(100).background(BSProgress.Background.danger).striped());
        panel.add(new BSText("<br>"));

        panel.add(new BSText("<h1>Animated stripes</h1>"));
        panel.add(new BSProgress(75).striped().animated());

        return panel;
    }

}
