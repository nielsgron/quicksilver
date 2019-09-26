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

package quicksilver.webapp.simpleui.icons;

import quicksilver.webapp.simpleui.html.components.HTMLImage;

public class FeatherIcon extends HTMLImage {

    public static FeatherIcon ACTIVITY = new FeatherIcon("/icons/feather/activity.svg");
    public static FeatherIcon DATABASE = new FeatherIcon("/icons/feather/database.svg");
    public static FeatherIcon DELETE = new FeatherIcon("/icons/feather/delete.svg");
    public static FeatherIcon SETTINGS = new FeatherIcon("/icons/feather/settings.svg");
    public static FeatherIcon TRASH = new FeatherIcon("/icons/feather/trash.svg");
    public static FeatherIcon TRASH_2 = new FeatherIcon("/icons/feather/trash-2.svg");

    protected FeatherIcon(String url) {
        super(url, "");
    }

    protected FeatherIcon(String url, String alt) {
        super(url, alt);
    }

    protected FeatherIcon(FeatherIcon icon, String alt) {
        super(icon.url, alt);
    }

    public static String getScriptLocal() {
        return "";
    }

    public static String getScriptCDN() {
        return "<script src=\"https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js\"></script>";
    }

}
