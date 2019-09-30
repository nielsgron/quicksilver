package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSFormRow extends BSRow {

    public BSFormRow(int columns) {
        super(columns);
    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("form-row");

        return cNames.toString();
    }

}
