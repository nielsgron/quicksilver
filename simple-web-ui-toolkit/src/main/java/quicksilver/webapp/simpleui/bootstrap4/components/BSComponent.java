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

package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.html.components.HTMLComponent;

public abstract class BSComponent extends HTMLComponent {

    public static final int HORIZONTAL_ALIGNMENT = 0;
    public static final int VERTICAL_ALIGNMENT = 1;

    private Type componentType = Type.PRIMARY;
    private Size componentSize = Size.NORMAL;
    private Alignment componentAlignment = Alignment.HORIZONTAL;
    private State componentState = State.ENABLED;

    public void setType(Type type) {
        componentType = type;
    }
    public Type getType() {
        return componentType;
    }

    public void setSize(Size size) {
        componentSize = size;
    }
    public Size getSize() {
        return componentSize;
    }

    public void setAlignment(Alignment alignment) {
        componentAlignment = alignment;
    }
    public Alignment getAlignment() {
        return componentAlignment;
    }

    public void setState(State state) {
        componentState = state;
    }
    public State getState() {
        return componentState;
    }

    public enum Type {

        PRIMARY("primary"),
        SECONDARY("secondary"),
        SUCCESS("success"),
        DANGER("danger"),
        WARNING("warning"),
        INFO("info"),
        LIGHT("light"),
        DARK("dark"),
        LINK("link"); // Link seems to be only used by buttons

        private final String typeName;

        Type(String typeString) {
            typeName = typeString;
        }

        String getTypeName() {
            return typeName;
        }

    }

    public enum Size {

        SMALL("sm"),
        LARGE("lg"),
        NORMAL("");

        private final String sizeName;

        Size(String sizeString) {
            sizeName = sizeString;
        }

        String getSizeName() {
            return sizeName;
        }

    }

    public enum Alignment {

        VERTICAL("vertical"),
        HORIZONTAL("horizontal");

        private final String alignmentName;

        Alignment(String typeString) {
            alignmentName = typeString;
        }

        String getAlignmentName() {
            return alignmentName;
        }

    }

    public enum State {

        ENABLED("enabled"),
        DISABLED("disabled");

        private final String stateName;

        State(String typeString) {
            stateName = typeString;
        }

        String getStateName() {
            return stateName;
        }

    }

}
