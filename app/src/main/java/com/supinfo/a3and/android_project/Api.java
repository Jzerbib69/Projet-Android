package com.supinfo.a3and.android_project;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    public void register(final String username, final String password, final String firstName, final String lastName, final String email){

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=register&username=" + username + "&password=" + password + "&firstname=" + firstName + "&lastname=" + lastName + "&email=" + email);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        //Log.e("string", stringBuilder.toString());

                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage(), e);
                }
            }
        });
        thread.start();
    }

    public void login(final String username, final String password){
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://supinfo.steve-colinet.fr/suptodo").newBuilder();
            urlBuilder.addQueryParameter("action", "login");
            urlBuilder.addQueryParameter("username", username);
            urlBuilder.addQueryParameter("password", password);
            String url = urlBuilder.build().toString();
            Log.e("url", url);

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
        }
    }

    public String logout(String username, String password){

        try {

            URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=logout&username="+username+"&password="+password);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                Log.e("string",stringBuilder.toString());
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }
}
