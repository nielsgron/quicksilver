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

package quicksilver.commons.config;

import java.io.File;

public class ConfigLogger extends SimpleConfig {

    private String applicationName;
    private String appFolderName;
    private File logsFile;

    public ConfigLogger(String applicationName, String appFolderName, File logsFile, File configFile) {
        super(configFile);

        this.applicationName = applicationName;
        this.appFolderName = appFolderName;

        this.logsFile = logsFile;

        addSections();
    }

    private void addSections() {

        setNonSectionComment("General options");
        addSection("property.", "Define properties to be used in rest of properties file");
        addSection("filter.", "Define any filters");
        addSection("appender.console.", "Configure appender for console output");
        addSection("appender.rolling.", "Configure appender for rolling logs");
        addSection("logger.rolling.", "Configure logger rolling attributes");
        addSection("rootLogger.", "Set root logger attributes");

    }

    public void setDefaultPropertiesAndValues() {

        String fileName = applicationName + "-log.log";

        setProperty("status", "error");
        setProperty("dest", "err");
        setProperty("name", "PropertiesConfig");

        setProperty("property.logsfolder", "${sys:user.home}/" + appFolderName + "/logs");

        setProperty("filter.threshold.type", "ThresholdFilter");
        setProperty("filter.threshold.level", "debug");

        setProperty("appender.console.type", "Console");
        setProperty("appender.console.name", "STDOUT");
        setProperty("appender.console.layout.type", "PatternLayout");
        setProperty("appender.console.layout.pattern", "%m%n");
        setProperty("appender.console.filter.threshold.type", "ThresholdFilter");
        setProperty("appender.console.filter.threshold.level", "error");

        setProperty("appender.rolling.type", "RollingFile");
        setProperty("appender.rolling.name", "RollingFile");
        setProperty("appender.rolling.fileName", "${logsfolder}/" + fileName );
        setProperty("appender.rolling.filePattern", fileName + "-%d{MM-dd-yy-HH-mm-ss}-%i.log.gz");
        setProperty("appender.rolling.layout.type", "PatternLayout");
        setProperty("appender.rolling.layout.pattern", "%d %p %C{1.} [%t] %m%n");
        setProperty("appender.rolling.policies.type", "Policies");
        setProperty("appender.rolling.policies.time.type", "TimeBasedTriggeringPolicy");
        setProperty("appender.rolling.policies.time.interval", "2");
        setProperty("appender.rolling.policies.time.modulate", "true");
        setProperty("appender.rolling.policies.size.type", "SizeBasedTriggeringPolicy");
        setProperty("appender.rolling.policies.size.size", "10MB");
        setProperty("appender.rolling.strategy.type", "DefaultRolloverStrategy");
        setProperty("appender.rolling.strategy.max", "50");

        setProperty("logger.rolling.name", "quicksilver.commons.app");
        setProperty("logger.rolling.level", "debug");
        setProperty("logger.rolling.additivity", "false");
        setProperty("logger.rolling.appenderRef.rolling.ref", "RollingFile");

        setProperty("rootLogger.level", "info");
        setProperty("rootLogger.appenderRef.stdout.ref", "STDOUT");

    }

}
