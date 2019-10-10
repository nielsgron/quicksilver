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

package quicksilver.commons.datafeed;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;

public class HttpRequester {

    /*
        - https://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url
        - https://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java

        The following methods are used to access the header fields and the contents after the connection is made to the remote object:

        getContent
        getHeaderField
        getInputStream
        getOutputStream

     */

    private String contentDisposition;
    private String contentEncoding;
    private String contentType;
    private long contentLength;
    private long urlDate;
    private long urlExpiration;
    private long urlLastModified;

    private int responseCode;

    // Apache Commons IO
//    public void requestURLToFileApache(URL source, File destination) throws IOException {
//
//        FileUtils.copyURLToFile(source, destination);
//
//    }

    public byte[] requestURLToMemory(URL source) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        requestURLToFile(source, baos);
        return baos.toByteArray();
    }

    public void requestURLToFile(URL source, File destination) throws IOException {
            // opens an output stream to save into file
            try(FileOutputStream outputStream = new FileOutputStream(destination)) {
                requestURLToFile(source, outputStream);
            }
    }

    // Java Http
    public void requestURLToFile(URL source, OutputStream outputStream ) throws IOException {
        // Get an instance of a HttpURLConnection
        URLConnection connection = source.openConnection();
        HttpURLConnection httpConnection = connection instanceof HttpURLConnection ? (HttpURLConnection) connection : null;

        // Set any options on the URLConnection before we connect
        // ...
        // httpConnection.setRequestMethod("GET / POST");

        // Initiate connection
        connection.connect();

        responseCode = httpConnection != null ? httpConnection.getResponseCode() : HttpURLConnection.HTTP_OK;

        // If the HTTP response code is OK, then process request
        if (responseCode == HttpURLConnection.HTTP_OK) {

            contentEncoding = connection.getContentEncoding();
            contentLength = connection.getContentLength();
            contentType = connection.getContentType();

            urlDate = connection.getDate();
            urlExpiration = connection.getExpiration();
            urlLastModified = connection.getLastModified();

            /*
            contentDisposition = connection.getHeaderField("Content-Disposition");

            String fileName = "";
            if (contentDisposition != null) {
                // extracts file name from header field
                int index = contentDisposition.indexOf("filename=");
                if (index > 0) {
                    fileName = contentDisposition.substring(index + 10,
                            contentDisposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = destination.getName();
            }
            */

            // opens input stream from the HTTP connection
            InputStream inputStream = connection.getInputStream();

            IOUtils.copy(inputStream, outputStream);

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        if(httpConnection != null) {
            httpConnection.disconnect();
        }

        printDebug();
    }

    public void printDebug() {

        System.out.println("Content-Encoding = " + contentEncoding);
        System.out.println("Content-Type = " + contentType);
        System.out.println("Content-Length = " + contentLength);
        System.out.println("Content-Disposition = " + contentDisposition);

        System.out.println("URL-Date = " + urlDate);
        System.out.println("URL-Expiration = " + urlExpiration);
        System.out.println("URL-LastModified = " + urlLastModified);

        // System.out.println("fileName = " + fileName);

    }

    public static void main(String[] args) {

        String fileURL = "https://jdbc.postgresql.org/download/postgresql-42.2.5.jar";
        String savedFileName = System.getProperty("user.home") + File.separator + "pg-download.jar";
        String apacheFileName = System.getProperty("user.home") + File.separator + "pg-apache.jar";

        HttpRequester requester = new HttpRequester();
        try {
            requester.requestURLToFile(new URL(fileURL), new File(savedFileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        try {
//            requester.requestURLToFileApache(new URL(fileURL), new File(apacheFileName));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

}
