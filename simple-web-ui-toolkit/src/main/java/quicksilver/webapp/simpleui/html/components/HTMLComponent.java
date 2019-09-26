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

package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class HTMLComponent {

    protected final String COMPONENT_ATTRIB_NAME = "Name";
    protected final String COMPONENT_ATTRIB_TAG_NAME = "tagName";
    protected final String COMPONENT_ATTRIB_TAG_CLOSE = "tagClose";
    protected final String COMPONENT_ATTRIB_END_WITH_LINEBREAK = "endLineBreak";

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

    protected abstract void defineAttributes();

    protected String getClassNames() {
        return null;
        // Return null by default, but can be overriden
    }
    protected String getStyle() {
        return null;
        // Return null by default, but can be overriden
    }
    public void renderBody(HtmlStream stream) {
        // Do nothing by default, but can be overriden
    }

    public void render(HtmlStream stream) {

        // Call this to tell the implementing component to build the attributes for rendering
        defineAttributes();

        Boolean eol = (Boolean)componentAttributes.get(COMPONENT_ATTRIB_END_WITH_LINEBREAK);

        // Open Tag
        stream.write("<");
        stream.write((String)componentAttributes.get(COMPONENT_ATTRIB_TAG_NAME));

        for ( int i = 0; i < tagAttributes.size(); i++ ) {
            String name = tagAttributes.get(i).getKey();
            String value = tagAttributes.get(i).getValue();

            if(value == null) {
                continue;
            }

            stream.write(" " + name + "=\"" + value + "\"" );
        }

        stream.writeln(">");

        // Body
        renderBody(stream);

        // Close Tag
        Boolean tagClose = (Boolean)componentAttributes.get(COMPONENT_ATTRIB_TAG_CLOSE);

        if ( Boolean.TRUE.equals(tagClose) ) {
            stream.write("</");
            stream.write((String) componentAttributes.get(COMPONENT_ATTRIB_TAG_NAME));

            if ( eol != null && eol.equals(Boolean.FALSE )) {
                stream.write(">");
            } else {
                stream.writeln(">");
            }

        }

    }

}
