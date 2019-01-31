package com.supinfo.a3and.android_project.Util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Api extends AppCompatActivity {

    boolean state = false;
    public ArrayList<String> todo = new ArrayList<>();
    public ArrayList<String> idTodo = new ArrayList<>();
    public ArrayList<String> userFriendTodo = new ArrayList<>();
    public String detailAlone;
    JSONObject jsonObject;

    public void register(final String username, final String password, final String firstName, final String lastName, final String email, Context context){
        //this.contextReference = new WeakReference<Context>(context);

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
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("true")){
                            Log.e("Erreur :", "L'utilisateur existe déjà");
                        }
                        else{
                            isConnected(true);
                            login(username, password);
                        }

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

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=login&username=" + username + "&password=" + password);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("false")){
                            Log.e("Erreur ", "Les identifiants sont incorrectes");

                        }
                        else{
                            isConnected(true);
                        }

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

    public void logout(){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=logout");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("false")){
                            Log.e("Erreur :", "Un problème du serveur est survenue");
                        }
                        else{
                            isConnected(true);
                        }
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

    public void listTodoList(final String username, final String password){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=list&username=" + username + "&password=" + password);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            idTodo.add(jsonObject.getString("id"));
                            userFriendTodo.add(jsonObject.getString("userinvited"));
                            todo.add(jsonObject.getString("todo"));
                        }
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("false")){
                            Log.e("Erreur ", "Une erreur est survenue sur la TodoList");
                        }
                        else{
                            isConnected(true);
                        }
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

    public void shareTodoList(final String username, final String password, final String usernameFriend){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=share&username=" + username + "&password=" + password+ "&user=" +usernameFriend);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("false")){
                            isConnected(false);
                        }
                        else{
                            isConnected(true);
                        }
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

    public void read(final String username, final String password, final String id){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=read&username=" + username + "&password=" + password + "&id=" + id);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                        for (int i = 0; i < jsonObject.length(); i++) {
                            detailAlone = (jsonObject.getString("todo"));
                        }
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

    public void updateTodoList(final String username, final String password, final String id, final String text){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://supinfo.steve-colinet.fr/suptodo?action=update&username=" + username + "&password=" + password+ "&id=" + id + "&todo=" + text);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        if((""+stringBuilder.charAt(11)+stringBuilder.charAt(12)+stringBuilder.charAt(13)+stringBuilder.charAt(14)+stringBuilder.charAt(15)).equals("false")){
                            isConnected(false);
                        }
                        else{
                            isConnected(true);
                        }

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

    public void isConnected(Boolean state){
        if(state){
            this.state=true;
        }
        else{
            this.state=false;
        }
    }

    public boolean isConnected(){
        return this.state;
    }
}
