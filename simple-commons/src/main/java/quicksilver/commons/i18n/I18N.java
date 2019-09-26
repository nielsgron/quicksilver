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

package quicksilver.commons.i18n;

import java.util.HashMap;
import java.util.Locale;

public class I18N {

    private static Locale defaultLocale;
    private static String resourceName;
    private static String resourcePath;
    private static Locale[] additionalLocales;

    private static HashMap<Locale, HashMap<String, String>> localizations = new HashMap<Locale, HashMap<String, String>>();

    public static void initialize(Locale defaultLocale, String resourceName, String resourcePath, Locale... additionalLocales) {

        I18N.defaultLocale = defaultLocale;
        I18N.resourceName = resourceName;
        I18N.resourcePath = resourcePath;
        I18N.additionalLocales = additionalLocales;

        // For Each additionalLocale, load the prompts from the resources into a HashMap and add to localizations
        for ( int i = 0; i < I18N.additionalLocales.length; i++ ) {
            HashMap<String, String> prompts = new HashMap<String, String>();
            // TODO : Load prompts into HashMap from resourcePath / resourceName

            localizations.put(I18N.additionalLocales[i], prompts);
        }

    }

    // Need list of prompts per language supported

    public static String get(String key, String defaultText, Object... msg) {

        return defaultText;
    }

    public static String get(String key, Locale locale, String defaultText, Object... msg) {

        if ( locale == defaultLocale ) {
            return defaultText;
        } else {
            HashMap<String, String> prompts = localizations.get(locale);
            if ( prompts != null ) {
                String prompt = prompts.get(key);
                if ( prompt != null ) {
                    return prompt;
                } else {
                    return defaultText;
                }
            } else {
                return defaultText;
            }
        }

        //return defaultText;
    }


}
