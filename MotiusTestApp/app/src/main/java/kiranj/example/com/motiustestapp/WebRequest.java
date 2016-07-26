package kiranj.example.com.motiustestapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by kiranj on 25-07-2016.
 */

public class WebRequest {

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public WebRequest() {
    }

    public String makeWebServiceCall(String url, int requestMethod) {
        return this.makeWebServiceCall(url, requestMethod, null);
    }

    public String makeWebServiceCall(String urlAddress, int requestMethod,
                                     HashMap<String, String> params) {
        URL url;
        String response = "";
        try {
            //Build the connection.
            url = new URL(urlAddress);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (requestMethod == POST) {
                connection.setRequestMethod("POST");
            } else if (requestMethod == GET) {
                connection.setRequestMethod("GET");
            }

            //Case when we pass the parameters to POST.
            if (params != null) {
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
                StringBuilder requestResult = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (first)
                        first = false;
                    else
                        requestResult.append("&");
                    requestResult.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    requestResult.append("=");
                    requestResult.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                bufferedWriter.write(requestResult.toString());

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
            }
            int responseCode = connection.getResponseCode();

            //If we get the proper response i.e response code 200.
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
