package com.nanosic.netget;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/3/30.
 */
public class HttpGetLearn {
    private static final String TAG = "HttpGetLearn";

    public static void requestByGet() throws IOException {
        String path = "https://reg.163.com/logins.jsp?id=helloworld&pwd=android";

        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setConnectTimeout(5000);

        urlConnection.connect();

        if (urlConnection.getResponseCode() == 200) {
            Log.d(TAG, "requestByGet: " + urlConnection.getInputStream().toString());
        } else {
            Log.d(TAG, "requestByGet: Get Failed");
        }
        urlConnection.disconnect();
    }

    public static void requestByPost() throws IOException {
        String path = "https://reg.163.com/logins.jsp";

        String params = "id=" + URLEncoder.encode("helloworld", "UTF-8")
                        + "&pwd=" + URLEncoder.encode("android", "UTF-8");

        byte[] postData = params.getBytes();

        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setConnectTimeout(5000);

        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestMethod("POST");
        urlConnection.setInstanceFollowRedirects(true);
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencode");

        urlConnection.connect();

        DataOutputStream dos = new DataOutputStream(urlConnection.getOutputStream());
        dos.write(postData);
        dos.flush();
        dos.close();

        if (urlConnection.getResponseCode() == 200) {
            Log.d(TAG, "Success requestByPost: " + urlConnection.getInputStream().toString());
        } else {
            Log.d(TAG, "requestByPost: Get Failed");
        }
        urlConnection.disconnect();
    }

}
