package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.commons.app.SimpleWebServer;
import quicksilver.commons.data.DataSet;
import quicksilver.commons.data.ExcelExporter;
import quicksilver.commons.data.TSDataSet;
import quicksilver.webapp.simpleui.HtmlPageBootstrapAppLayout;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSFigurePanel;
import quicksilver.webapp.simpleui.bootstrap4.components.*;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import spark.Request;
import spark.Response;
import tech.tablesaw.charts.Chart;
import tech.tablesaw.charts.ChartBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.halt;

public class QuickPage extends HtmlPageBootstrapAppLayout {

    private BSNavTab navTab = null;
    private BSButtonToolbar toolbar = null;
    private BSPanel navView = null;

    private QuickBodyPanel body = new QuickBodyPanel();
    private ArrayList viewList = new ArrayList();

    public QuickPage() {

    }

    public void setNavTab(BSNavTab navTab) {
        this.navTab = navTab;
    }

    public BSNavTab getNavTab() {
        return navTab;
    }

    public void setToolbar(BSButtonToolbar toolbar) {
        this.toolbar = toolbar;
    }

    public BSButtonToolbar getToolBar() {
        return toolbar;
    }

    public void setNavView(BSPanel navView) {
        this.navView = navView;
    }

    public BSPanel getNavView() {
        return navView;
    }

    public void addSideBarMenu(String headerName, String[] sidebarNames, String[] sidebarUrls, String activeItemName ) {
        body.addSideBarMenu(headerName, sidebarNames, sidebarUrls, activeItemName);
    }

    public void addBodyView(String title, Object view) {
        viewList.add(new BodyView(title, view));
    }

    protected BSPanel createContentPanelTop() {

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak());
        panel.add(getNavTab());

        // If there is a toolbar, add it to the top panel, which contains the navbar
        BSButtonToolbar toolbar = getToolBar();
        if ( toolbar != null ) {
            panel.add(new BSNavbarToolbarContainer(toolbar));
        }

        return panel;
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel fullBodyPanel = new BSPanel();
        fullBodyPanel.add(new HTMLLineBreak(1));
        if ( navView != null ) {
            fullBodyPanel.add(navView);
        }

        for ( int i = 0; i < viewList.size(); i++ ) {

            BSCard cardView = null;
            Object view = viewList.get(i);
            if ( view instanceof BodyView ) {
                cardView = buildCardView((BodyView)view);
            } else if ( view instanceof List) {
                List subViewList = (List)view;
                for ( int j = 0; j < subViewList.size(); j++ ) {
                    Object subView = subViewList.get(j);
                    if ( subView instanceof BodyView ) {

                    }
                }
            }

            body.addRowOfColumns(cardView);
        }

        fullBodyPanel.add(body);

        fullBodyPanel.add(new HTMLLineBreak(1));

        return fullBodyPanel;
    }

    protected BSCard buildCardView(BodyView view) {

        if ( view.view instanceof DataSet) {
            BSTable table = new BSTable((DataSet)view.view, view.title);
            customizeTable(view.title, table);
            return new BSCard(table, view.title);
        } else if ( view.view instanceof ChartBuilder ){
            Chart theChart = ((ChartBuilder)view.view).build();
            TSFigurePanel figurePanel = new TSFigurePanel(theChart, ((ChartBuilder)view.view).getDivName());
            return new BSCard(figurePanel, view.title);
        } else {
            return new BSCard(new BSPanel(), view.title);
        }

    }

    protected void customizeTable(String title, BSTable table) {
        // Can be overwritten to customize table
    }

    protected ArrayList<TSDataSet> getDataSetList() {
        ArrayList<TSDataSet> list = new ArrayList<>();

        for ( int i = 0; i < viewList.size(); i++ ) {
            Object view = viewList.get(i);
            if ( view instanceof BodyView ) {
                if ( ((BodyView)view).view instanceof TSDataSet ) {
                    list.add((TSDataSet)((BodyView)view).view);
                }
            } else if ( view instanceof List) {
                List subViewList = (List)view;
                for ( int j = 0; j < subViewList.size(); j++ ) {
                    Object subView = subViewList.get(j);
                    if ( subView instanceof BodyView ) {
                        if ( ((BodyView)subView).view instanceof TSDataSet ) {
                            list.add((TSDataSet)((BodyView)subView).view);
                        }
                    }
                }
            }

        }

        return list;
    }

    public Object returnExportFile(Request request, Response response, String fileName, boolean bCompressed) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ExcelExporter exporter = new ExcelExporter();

            ArrayList<TSDataSet> dsList = getDataSetList();
            for (int i = 0; i < dsList.size(); i++) {
                exporter.addDataSet(dsList.get(i), "Sheet" + (i+1));
            }

            exporter.export(outputStream);
        } catch ( Throwable e ) {
            e.printStackTrace();
        }

        String acceptType = "application/octet-stream";  // binary

        try {
            SimpleWebServer.writeBinaryToResponse(outputStream.toByteArray(), acceptType, fileName, response, bCompressed);
        } catch (Exception e) {
            halt(405, "server error");
        }

        return response.raw();
    }

    private class BodyView {

        public String title;
        public Object view;

        BodyView(String title, Object view) {
            this.title = title;
            this.view = view;
        }

    }

}