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

package quicksilver.commons.config;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeSet;

public class SectionedProperties extends SortedProperties {

    String nonSectionComment = "No section";
    HashMap<String, String> sections = new HashMap<String, String>();

    public void setNonSectionComment(String comment) {
        nonSectionComment = comment;
    }

    public void addSection(String prefix, String comment) {
        if (comment == null) {
            comment = "";
        }
        sections.put(prefix, comment);
    }

    public void write(Properties p, File file) throws IOException {
        write(p, new FileOutputStream(file));
    }

    public void write(Properties p, Writer writer) throws IOException {
        BufferedWriter w;
        if (writer instanceof BufferedWriter) {
            w = (BufferedWriter) writer;
        }
        else {
            w = new BufferedWriter(writer);
        }

        writeSorted(p, w);
    }

    public void write(Properties p, OutputStream out) throws IOException {
        writeSorted(p, new BufferedWriter(new OutputStreamWriter(out, "8859_1")));
    }

    protected void writeSorted(Properties p, BufferedWriter wr) throws IOException {
        synchronized (p) {
            TreeSet<String> keys = new TreeSet<String>();
            for (String key : p.stringPropertyNames()) {
                keys.add(key);
            }

            HashMap<String, String> cats = new HashMap<String, String>(sections);
            HashMap<Object, Object> nonSectionKeys = new HashMap<Object, Object>();

            // Write out all properties without sections
            boolean bFirstNonSectionKey = true;
            for (String key : keys) {
                String comment = sectionStarts(cats, key, false);
                if (comment == null || comment.length() == 0) {
                    if ( bFirstNonSectionKey ) {
                        wr.newLine();
                        writeComments(wr, nonSectionComment);
                        bFirstNonSectionKey = false;
                    }

                    String val = p.getProperty(key);
                    key = saveConvert(key, true);
                    val = saveConvert(val, false);
                    wr.write(key + "=" + val);
                    wr.newLine();

                    nonSectionKeys.put(key, key);
                }
            }

            // Write out all properties in sections
            for (String key : keys) {
                if ( nonSectionKeys.containsKey(key) ) {
                    continue;
                }

                String comment = sectionStarts(cats, key, true);
                if (comment != null) {
                    wr.newLine();
                    writeComments(wr, comment);
                }

                String val = p.getProperty(key);
                key = saveConvert(key, true);
                val = saveConvert(val, false);
                wr.write(key + "=" + val);
                wr.newLine();
            }
        }
        wr.flush();
    }

    public void store(OutputStream out, String comments)
            throws IOException {

        write(this, out);

    }

    // returns comment of the section, otherwise null
    protected String sectionStarts(HashMap<String, String> sections, String key, boolean removeSection) {
        if (sections.size() > 0) {
            for (String section : sections.keySet()) {
                if (key.startsWith(section)) {
                    if ( removeSection ) {
                        return sections.remove(section);
                    } else {
                        return section;
                    }
                }
            }
        }
        return null;
    }

    // java.util.Properties.saveConvert
    protected String saveConvert(String theString, boolean escapeSpace) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }

        StringBuffer outBuffer = new StringBuffer(bufLen);

        for (int i = 0; i < len; i++) {
            char c = theString.charAt(i);
            if ((c > 61) && (c < 127)) {
                if (c == '\\') {
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(c);
                continue;
            }
            switch (c) {
                case ' ':
                    if (i == 0 || escapeSpace) {
                        outBuffer.append('\\');
                    }
                    outBuffer.append(' ');
                    break;
                case '\t':
                    outBuffer.append('\\');
                    outBuffer.append('t');
                    break;
                case '\n':
                    outBuffer.append('\\');
                    outBuffer.append('n');
                    break;
                case '\r':
                    outBuffer.append('\\');
                    outBuffer.append('r');
                    break;
                case '\f':
                    outBuffer.append('\\');
                    outBuffer.append('f');
                    break;
                case '=':
                case ':':
                case '#':
                case '!':
                    outBuffer.append('\\');
                    outBuffer.append(c);
                    break;
                default:
                    if ((c < 0x0020) || (c > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex(c >> 12));
                        outBuffer.append(toHex(c >> 8));
                        outBuffer.append(toHex(c >> 4));
                        outBuffer.append(toHex(c));
                    }
                    else {
                        outBuffer.append(c);
                    }
            }
        }
        return outBuffer.toString();
    }

    // java.util.Properties.writeComments
    protected void writeComments(BufferedWriter bw, String comments) throws IOException {
        bw.write("# ");
        int len = comments.length();
        int current = 0;
        int last = 0;
        char[] uu = new char[6];
        uu[0] = '\\';
        uu[1] = 'u';
        while (current < len) {
            char c = comments.charAt(current);
            if (c > '\u00ff' || c == '\n' || c == '\r') {
                if (last != current) {
                    bw.write(comments.substring(last, current));
                }
                if (c > '\u00ff') {
                    uu[2] = toHex((c >> 12) & 0xf);
                    uu[3] = toHex((c >> 8) & 0xf);
                    uu[4] = toHex((c >> 4) & 0xf);
                    uu[5] = toHex(c & 0xf);
                    bw.write(new String(uu));
                }
                else {
                    bw.newLine();
                    if (c == '\r' && current != len - 1 && comments.charAt(current + 1) == '\n') {
                        current++;
                    }
                    if (current == len - 1 || (comments.charAt(current + 1) != '#' && comments.charAt(current + 1) != '!')) {
                        bw.write("#");
                    }
                }
                last = current + 1;
            }
            current++;
        }
        if (last != current) {
            bw.write(comments.substring(last, current));
        }
        bw.newLine();
    }

    // java.util.Properties.toHex
    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    // java.util.Properties.hexDigit
    private static final char[] hexDigit = {
            '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };

}
