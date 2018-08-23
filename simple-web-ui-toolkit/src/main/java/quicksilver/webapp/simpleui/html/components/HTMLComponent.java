package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class HTMLComponent {

    protected final String COMPONENT_ATTRIB_NAME = "Name";
    protected final String COMPONENT_ATTRIB_TAG_NAME = "tagName";
    protected final String COMPONENT_ATTRIB_TAG_CLOSE = "tagClose";

    protected HashMap<String, Object> componentAttributes = new HashMap<String, Object>();
    protected ArrayList<AbstractMap.SimpleEntry<String, String>> tagAttributes = new ArrayList<AbstractMap.SimpleEntry<String, String>>();

    protected void putComponentAttribute(String name, Object value) {
        componentAttributes.put(name, value);
    }
    protected void addTagAttribute(String name, String value) {
        tagAttributes.add(new AbstractMap.SimpleEntry<String, String>(name, value));
    }
    protected void insertTagAttribute(int index, String name, String value) {
        tagAttributes.add(index, new AbstractMap.SimpleEntry<String, String>(name, value));
    }
    protected void removeTagAttribute(String name) {
        for ( int i = 0; i < tagAttributes.size(); i++ ) {
            AbstractMap.SimpleEntry entry = tagAttributes.get(i);
            if ( entry.getKey().equals(name)) {
                tagAttributes.remove(i);
                break;
            }
        }
    }

    public void renderBody(HtmlStream stream) {
        // Do nothing by default, but can be overriden
    }

    public void render(HtmlStream stream) {

        // Open Tag
        stream.write("<");
        stream.write((String)componentAttributes.get(COMPONENT_ATTRIB_TAG_NAME));

        for ( int i = 0; i < tagAttributes.size(); i++ ) {
            String name = tagAttributes.get(i).getKey();
            String value = tagAttributes.get(i).getValue();

            stream.write(" " + name + "=\"" + value + "\"" );
        }

        stream.writeln(">");

        // Body
        renderBody(stream);

        // Close Tag
        Boolean tagClose = (Boolean)componentAttributes.get(COMPONENT_ATTRIB_TAG_CLOSE);

        if ( tagClose != null && tagClose ) {
            stream.write("</");
            stream.write((String) componentAttributes.get(COMPONENT_ATTRIB_TAG_NAME));
            stream.writeln(">");
        }

    }

}
