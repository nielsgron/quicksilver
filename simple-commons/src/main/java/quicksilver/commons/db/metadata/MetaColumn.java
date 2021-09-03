package quicksilver.commons.db.metadata;

public class MetaColumn {

    protected String name;
    protected String dataType;
    protected String length;
    protected String precision;
    protected String scale;
    protected boolean isNotNull = false;

    protected String typeDefinition;

    public MetaColumn(String name) {
        this.name = name;
        this.dataType = "varchar";

        buildTypeDefinition();
    }

    public MetaColumn(String name, String dataType) {
        this.name = name;
        this.dataType = dataType;

        buildTypeDefinition();
    }

    public MetaColumn(String name, String dataType, boolean isNotNull) {
        this.name = name;
        this.dataType = dataType;
        this.isNotNull = isNotNull;

        buildTypeDefinition();
    }

    public MetaColumn(String name, String dataType, String length) {
        this.name = name;
        this.dataType = dataType;
        this.length = length;

        buildTypeDefinition();
    }

    public MetaColumn(String name, String dataType, String length, boolean isNotNull) {
        this.name = name;
        this.dataType = dataType;
        this.length = length;
        this.isNotNull = isNotNull;

        buildTypeDefinition();
    }

    public MetaColumn(String name, String dataType, String precision, String scale) {
        this.name = name;
        this.dataType = dataType;
        this.precision = precision;
        this.scale = scale;

        buildTypeDefinition();
    }

    public MetaColumn(String name, String dataType, String precision, String scale, boolean isNotNull) {
        this.name = name;
        this.dataType = dataType;
        this.precision = precision;
        this.scale = scale;
        this.isNotNull = isNotNull;

        buildTypeDefinition();
    }

    private void buildTypeDefinition() {
        StringBuilder definitionBuilder = new StringBuilder();

        if ( dataType.equals("currency")) {
            definitionBuilder.append("double");
        } else if ( dataType.equals("percent")) {
            definitionBuilder.append("double");
        } else if ( dataType.equals("ratio")) {
            definitionBuilder.append("double");
        } else {
            definitionBuilder.append(dataType);
        }

        if ( length != null ) {

            definitionBuilder.append("(" + length+ ")");

        } else {

            if ( precision != null && scale != null ) {

                definitionBuilder.append("(" + precision + "," + scale + ")");

            } else if ( precision != null ) {

                definitionBuilder.append("(" + precision + ")");

            }

        }

        if ( isNotNull ) {
            definitionBuilder.append(" NOT NULL");
        }

        typeDefinition = definitionBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public String getTypeDefinition() {
        return typeDefinition;
    }

}
