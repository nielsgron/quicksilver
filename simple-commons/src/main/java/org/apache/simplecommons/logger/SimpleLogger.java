/*
 * Copyright 2018 Niels Gron All Rights Reserved.
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

package org.apache.simplecommons.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleLogger extends Logger {

    private static String NAME = "system";
    private static SimpleLogger INSTANCE = new SimpleLogger("resourceBundleName");

    private Logger applicationLogger = null;

    public SimpleLogger(String resourceBundleName) {
        super(NAME, resourceBundleName);
    }

    public void init() {

        applicationLogger = Logger.getLogger( NAME );

        FileHandler fh = null;
        try {
            fh = new FileHandler("loggerExample.log", false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        if ( fh != null ) {
            applicationLogger.addHandler(fh);
            applicationLogger.setLevel(Level.CONFIG);
            //applicationLogger.setFormatter(new SimpleFormatter());
        }
    }

    public static SimpleLogger get() {
        return INSTANCE;
    }

    public void logSevere() {

    }

    public void logWarning() {

    }

    public void logInfo() {

    }

    public void logConfigInfo() {

    }

    public void logTraceFine() {

    }

    public void logTraceFiner() {

    }

    public void logTraceFinest() {

    }

}
