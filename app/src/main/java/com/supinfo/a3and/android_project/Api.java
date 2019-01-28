package com.supinfo.a3and.android_project;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api extends AppCompatActivity{



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
                        Log.e("string", stringBuilder.toString());
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("false")){
                            Log.e("string", "Echec de l'inscription, utilisateur déjà éxistant.");

                        }
                        else{
                            Log.e("string","Inscription réussie !");
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


    /*public boolean isUserAlreadyExists(){
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
                Log.e("string", stringBuilder.toString());
                //return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
        }
        return user;
    }*/

}
