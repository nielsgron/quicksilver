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

package quicksilver.webapp.simpleui.bootstrap4.components;

/*
    Example :

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_dropdowns.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/dropdowns/
 */

public class BSDropdown extends BSComponentContainer {

    private boolean isBtnGroup = false;

    private Direction direction = Direction.DOWN;

    public enum Direction {

        DOWN("down"),
        UP("up"),
        LEFT("left"),
        RIGHT("right");

        private final String dirName;

        Direction(String dirString) {
            dirName = dirString;
        }

        String getDirectionName() {
            return dirName;
        }

    }

    public BSDropdown() {

    }

    public BSDropdown(boolean isBtnGroup) {
        this.isBtnGroup = isBtnGroup;
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Dropdown");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", getClassNames());

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("btn-group");

        switch (direction) {

            case DOWN:
                cNames.append(" dropdown");
                break;

            case UP:
                cNames.append(" dropup");
                break;

            case RIGHT:
                cNames.append(" dropright");
                break;

            case LEFT:
                cNames.append(" dropleft");
                break;

        }
        cNames.append(" mr-2");

        return cNames.toString();
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

}
