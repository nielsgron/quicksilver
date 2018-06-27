package org.apache.webapp.simpleui.bootstrap4.components;

public enum BSComponentType {

    PRIMARY("primary"),
    SECONDARY("secondary"),
    SUCCESS("success"),
    DANGER("danger"),
    WARNING("warning"),
    INFO("info"),
    LIGHT("light"),
    DARK("dark");

    private final String typeName;

    BSComponentType(String typeString) {
        typeName = typeString;
    }

    String getTypeName() {
        return typeName;
    }

}
