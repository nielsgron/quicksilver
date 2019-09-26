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

public class ConfigEmailServer extends SimpleConfig {

    private String DEFAULT_EMAILSERVER_HOST = "localhost";
    private String DEFAULT_EMAILSERVER_PORT = "3306";
    private String DEFAULT_EMAILSERVER_USERNAME = "username";
    private String DEFAULT_EMAILSERVER_PASSWORD = "password";

    private String PROP_EMAILSERVER_HOST = "simplejavamail.smtp.host";
    private String PROP_EMAILSERVER_PORT = "simplejavamail.smtp.port";
    private String PROP_EMAILSERVER_USERNAME = "simplejavamail.smtp.username";
    private String PROP_EMAILSERVER_PASSWORD = "simplejavamail.smtp.password";

    public ConfigEmailServer(File configFile) {
        super(configFile);
        addSections();
    }

    public ConfigEmailServer(File configFile, String host, String port, String username, String password) {
        super(configFile);

        DEFAULT_EMAILSERVER_HOST = host;
        DEFAULT_EMAILSERVER_PORT = port;
        DEFAULT_EMAILSERVER_USERNAME = username;
        DEFAULT_EMAILSERVER_PASSWORD = password;

        addSections();
    }

    private void addSections() {

        addSection("simplejavamail.javaxmail.", "Java Mail Properties");
        addSection("simplejavamail.transportstrategy.", "Transport method : SMTP, SMTPS, SMTP_TLS");
        addSection("simplejavamail.smtp.", "SMTP Server properties");
        addSection("simplejavamail.proxy.", "Proxy properties");
        addSection("simplejavamail.defaults.", "Email Defaults");
        addSection("simplejavamail.transport.", "Transport mode to enable logging only");
        addSection("simplejavamail.opportunistic.", "Opportunistic TLS");

    }

    public String getHost(String defaultHost) {
        return getPropertyOrSetDefault(PROP_EMAILSERVER_HOST, defaultHost);
    }

    public String getPort(String defaultPort) {
        return getPropertyOrSetDefault(PROP_EMAILSERVER_PORT, defaultPort);
    }

    public String getUsername(String defaultUsername) {
        return getPropertyOrSetDefault(PROP_EMAILSERVER_USERNAME, defaultUsername);
    }

    public String getPassword(String defaultPassword) {
        return getPropertyOrSetDefault(PROP_EMAILSERVER_PASSWORD, defaultPassword);
    }

    public void setDefaultPropertiesAndValues() {

        setProperty("simplejavamail.javaxmail.debug", "true");
        setProperty("simplejavamail.transportstrategy", "SMTPS");
        setProperty("simplejavamail.smtp.host", "smtp.default.com");
        setProperty("simplejavamail.smtp.port", "25");
        setProperty("simplejavamail.smtp.username", "username");
        setProperty("simplejavamail.smtp.password", "password");
        setProperty("simplejavamail.proxy.host", "proxy.default.com");
        setProperty("simplejavamail.proxy.port", "1080");
        setProperty("simplejavamail.proxy.username", "username proxy");
        setProperty("simplejavamail.proxy.password", "password proxy");
        setProperty("simplejavamail.proxy.socks5bridge.port", "1081");
//        setProperty("simplejavamail.defaults.subject", "Sweet News");
//        setProperty("simplejavamail.defaults.from.name", "From Default");
//        setProperty("simplejavamail.defaults.from.address", "from@default.com");
//        setProperty("simplejavamail.defaults.replyto.name", "Reply-To Default");
//        setProperty("simplejavamail.defaults.replyto.address", "reply-to@default.com");
//        setProperty("simplejavamail.defaults.to.name", "To Default");
//        setProperty("simplejavamail.defaults.to.address", "to@default.com");
//        setProperty("simplejavamail.defaults.cc.name", "CC Default");
//        setProperty("simplejavamail.defaults.cc.address", "cc@default.com");
//        setProperty("simplejavamail.defaults.bcc.name", "");
//        setProperty("simplejavamail.defaults.bcc.address", "bcc1@default.com;bcc2@default.com");
        setProperty("simplejavamail.defaults.poolsize", "10");
        setProperty("simplejavamail.defaults.sessiontimeoutmillis", "60000");
//        setProperty("simplejavamail.transport.mode.logging.only", "true");
        setProperty("simplejavamail.opportunistic.tls", "false");

    }

}
