package com.supinfo.a3and.android_project;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api extends AppCompatActivity {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;

    EditText usernameText, firstNameText, lastNameText,emailText, passwordText;


    public void getData(){
        usernameText = findViewById(R.id.edtUsername);
        firstNameText = findViewById(R.id.edtFirstName);
        lastNameText = findViewById(R.id.edtLastName);
        emailText = findViewById(R.id.edtEmail);
        passwordText = findViewById(R.id.edtPassword);
    }

    public String register(){

        try {
            //http://supinfo.steve-colinet.fr/suptodo/?action=register&username=teszzt12&password=test12&firstname=test12&lastname=test12&email=test12

            URL url = new URL("supinfo.steve-colinet.fr/suptodo?action=register&username="+usernameText+"&password="+passwordText+"firstname="+firstNameText+"&lastname="+lastNameText+"&email="+emailText);
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
