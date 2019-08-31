package quicksilver.webapp.simpleui.bootstrap4.layouts;

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.bootstrap4.components.BSContainer;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSRow;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;

public class BSGridLayout implements BSLayoutManager  {

    private int rows;
    private int columns;
    private int currentLayoutPosition = 0;

    private BSContainer childrenContainer = new BSContainer(false,0,0);

    public BSGridLayout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        for ( int i = 0; i < rows; i++ ) {
            childrenContainer.add(new BSRow(columns));
        }

    }

    @Override
    public HTMLComponent add(HTMLComponent component) {
        add(component, null);
        return component;
    }

    @Override
    public HTMLComponent add(HTMLComponent component, Object constraint) {
        int currentRow = currentLayoutPosition / columns;
        int currentColumn = currentLayoutPosition - (currentRow * columns);

        BSPanel panel = getCellPanel(currentRow, currentColumn);
        panel.add(component);

        currentLayoutPosition++;

        return component;
    }

    @Override
    public void render(HtmlStream stream) {
        childrenContainer.render(stream);
    }

    private BSPanel getCellPanel(int row, int column) {

        BSPanel panel = (BSPanel)childrenContainer.getRow(row).getColumn(column).get(0);
        if ( panel == null ) {
            panel = new BSPanel();
            childrenContainer.getRow(row).getColumn(column).add(panel);
            childrenContainer.getRow(row).getColumn(column).setColumnWeight( 12/columns);
        }
        return panel;
    }

}
