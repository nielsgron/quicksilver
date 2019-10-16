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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.IOUtils;

public class OkHttpRequester extends AbstractHttpRequester {

    private int responseCode;

    public final OkHttpClient client = new OkHttpClient.Builder()
            //.cache(new Cache(cacheDir, cacheSize))
            .build();

    @Override
    public void requestURL(URL source, OutputStream outputStream) throws IOException {
        //turns out OkHttp doesn't handle file://
        if ("file".equals(source.getProtocol())) {
            try (FileInputStream fos = new FileInputStream(new File(source.toURI()))) {
                IOUtils.copy(fos, outputStream);
                return;
            } catch (URISyntaxException use) {
                //ignore, let OkHttp also try...
            }
        }

        Request request = new Request.Builder()
                .url(source)
                .build();

        try (Response response = client.newCall(request).execute()) {
            responseCode = response.code();

            // If the HTTP response code is OK, then process request
            if (responseCode == 200) {
                ResponseBody body = response.body();
                if (body == null) {
                    throw new IOException("Null body");
                }
                IOUtils.copy(body.byteStream(), outputStream);

                System.out.println("File downloaded");
            } else {
                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
        }
    }

}
