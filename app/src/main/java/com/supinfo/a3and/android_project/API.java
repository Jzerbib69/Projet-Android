package com.supinfo.a3and.android_project;

import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;

    public void register(){
        URL url = new URL("supinfo.steve-colinet.fr/suptodo");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {

        }
        finally {
            urlConnection.disconnect();
        }
    }
}
