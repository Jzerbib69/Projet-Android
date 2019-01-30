package com.supinfo.a3and.android_project;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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
    Context context;
    public void register(final String username, final String password, final String firstName, final String lastName, final String email, final Context context){
    this.context = context;
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

                        Log.e("string", stringBuilder.toString());
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("false")){
                            Log.e("string", "Echec de l'inscription, utilisateur déjà éxistant.");

                           postToastMessage("Echec de l'inscription, utilisateur déjà existant.");
                        }
                        else{
                            Log.e("string","Inscription réussie !");
                            postToastMessage("Inscription réussie !");
                        }
                        //return stringBuilder.toString();
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

    public void postToastMessage(final String message) {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
