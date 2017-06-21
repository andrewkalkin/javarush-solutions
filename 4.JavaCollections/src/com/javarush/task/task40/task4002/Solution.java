package com.javarush.task.task40.task4002;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("https://requestb.in/ztvn66zt", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {


        HttpClient client = getHttpClient();
        HttpPost request = new HttpPost(url);

        request.addHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> valPair  = new ArrayList<>();
        String[] parameters = urlParameters.split("&");

        String[] keyValue;

        for (String str: parameters) {
            keyValue = str.split("=");
            valPair.add(new BasicNameValuePair(keyValue[0], keyValue.length > 1 ? keyValue[1]: ""));
        }

        request.setEntity(new UrlEncodedFormEntity(valPair));

        HttpResponse response = client.execute(request);

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
