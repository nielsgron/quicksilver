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

package quicksilver.commons.app;

import java.io.File;

public class ApplicationHomePath {

    private String appFolderName;

    private File applicationHomePath;
    private File configPath;
    private File logPath;
    private File dataPath;

    public ApplicationHomePath(String appFolderName) {

        String userHome = System.getProperty("user.home");
        this.appFolderName = appFolderName;

        applicationHomePath = new File(userHome, appFolderName);
        configPath = new File(applicationHomePath, "config");
        logPath = new File(applicationHomePath, "logs");
        dataPath = new File(applicationHomePath, "data");

        applicationHomePath.mkdirs();
        configPath.mkdirs();
        logPath.mkdirs();
        dataPath.mkdirs();

    }

    public File getApplicationHomePath() {
        return applicationHomePath;
    }

    public File getConfigPath() {
        return configPath;
    }

    public File getLogPath() {
        return logPath;
    }

    public File getDataPath() {
        return dataPath;
    }

    public String getAppFolderName() {
        return appFolderName;
    }

}
