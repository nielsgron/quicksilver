/*
 * Copyright 2018-2020 Niels Gron and Contributors All Rights Reserved.
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
package quicksilver.webapp.simpleui.bootstrap4.components;

/**
 * @see
 * <a href='https://getbootstrap.com/docs/4.1/components/navs/'>Bootstrap
 * Docs</a>
 */
/* package */ class BSNavLink extends BSHyperlink {

    private boolean active = false;
    private boolean disabled = false;

    public BSNavLink(String text, String hyperlinkURL) {
        super(text, hyperlinkURL);
    }

    @Override
    protected String getClassNames() {
        StringBuilder sb = new StringBuilder();
        sb.append("nav-link");
        if (active) {
            sb.append(" active");
        }
        if (disabled) {
            sb.append(" disabled");
        }
        return sb.toString();
    }

    public void setActive(boolean b) {
        this.active = b;
    }

    public boolean isActive() {
        return active;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

}
