package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

import java.util.ArrayList;

public class HTMLComponentContainer extends HTMLComponent {

    protected ArrayList<HTMLComponent> children = new ArrayList<HTMLComponent>();

    public HTMLComponent add(HTMLComponent component) {
        if ( component == null ) {
            return null;
        }
        children.add(component);

        return component;
    }

    public HTMLComponent add(HTMLComponent component, Object constraint) {
        if ( component == null ) {
            return null;
        }
        children.add(component);

        return component;
    }

    public void remove(HTMLComponent component) {
        if ( component == null ) {
            return;
        }
        children.remove(component);
    }

    public HTMLComponent get(int index) {
        if ( children.size() == 0 ) {
            return null;
        }
        if ( index >= children.size() ) {
            return null;
        }
        return children.get(index);
    }

    public int getChildrenCount() {
        return children.size();
    }

    public void render(HtmlStream stream) {

        // Render each of the children components
        for (int i = 0; i < children.size(); i++) {
            children.get(i).render(stream);
        }

    }

}
