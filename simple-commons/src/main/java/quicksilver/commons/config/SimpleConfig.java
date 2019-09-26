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

package quicksilver.commons.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SimpleConfig extends SectionedProperties {

    private File configFile;

    public SimpleConfig(File configFile) {
        this.configFile = configFile;

        if ( configFile.exists() ) {
            load();
        } else {
            //this.configFile.mkdirs();
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public void load() {

        try {
            FileInputStream fis = new FileInputStream(configFile);
            this.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save() {

        try {
            FileOutputStream fos = new FileOutputStream(configFile);
            this.store(fos, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDefaultPropertiesAndValues() {
        // Default implementation doesn't do anything
    }

}
