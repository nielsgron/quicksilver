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

package quicksilver.commons.email;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import quicksilver.commons.config.ConfigEmailServer;

import java.util.HashMap;
import java.util.Map;

public class EmailService {

    private static EmailService defaultService;
    private static HashMap<String, EmailService> services = new HashMap<String, EmailService>();

    private Mailer mailer;

    public EmailService(String host, String port, String username, String password, TransportStrategy transport) {

        mailer = MailerBuilder
                .withSMTPServer(host, 587, username, password)
                .withTransportStrategy(transport)
                //.withProxy("socksproxy.host.com", 1080, "proxy user", "proxy password")
                .buildMailer();

    }

    public static void createDefaultInstance(ConfigEmailServer config) {

        defaultService = new EmailService(
                config.getHost(null),
                config.getPort(null),
                config.getUsername(null),
                config.getPassword(null),
                TransportStrategy.SMTP_TLS
        );

    }

    public static void createInstance(String name, ConfigEmailServer config) {

        services.put(name, new EmailService(
                config.getHost(null),
                config.getPort(null),
                config.getUsername(null),
                config.getPassword(null),
                TransportStrategy.SMTP_TLS
        ));

    }

    public static EmailService get() {
        return defaultService;
    }

    public static EmailService get(String name) {
        return services.get(name);
    }

    public void sendEmail() {

        Email email = EmailBuilder.startingBlank()
                .to("lollypop", "lolly.pop@somemail.com")
                .to("C. Cane", "candycane@candyshop.org")
                .ccWithFixedName("C. Bo group", "chocobo1@candyshop.org", "chocobo2@candyshop.org")
                //.withRecipientsWithFixedName("Tasting Group", BCC, "taster1@cgroup.org;taster2@cgroup.org;tester <taster3@cgroup.org>")
                .bcc("Mr Sweetnose <snose@candyshop.org>")
                .withReplyTo("lollypop", "lolly.pop@othermail.com")
                .withSubject("hey")
                .withHTMLText("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>")
                .withPlainText("Please view this email in a modern email client!")
                //.withEmbeddedImage("wink1", imageByteArray, "image/png")
                //.withEmbeddedImage("wink2", imageDatesource)
                //.withAttachment("invitation", pdfByteArray, "application/pdf")
                //.withAttachment("dresscode", odfDatasource)
                .withHeader("X-Priority", 5)
                .withReturnReceiptTo()
                .withDispositionNotificationTo("notify-read-emails@candyshop.com")
                .withBounceTo("tech@candyshop.com")
                //.signWithDomainKey(privateKeyData, "somemail.com", "selector")
                .buildEmail();


        mailer.sendMail(email);

    }

    public static void shutdown() {

        if ( defaultService != null ) {
            defaultService.shutdownService();
        }
        for ( Map.Entry<String, EmailService> entry : services.entrySet() ) {
            entry.getValue().shutdownService();
        }

    }

    public void shutdownService() {
        if ( mailer != null ) {
            try {
                // mailer.shutdown();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }


}
