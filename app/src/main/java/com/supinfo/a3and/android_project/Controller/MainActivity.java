package com.supinfo.a3and.android_project.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.supinfo.a3and.android_project.Model.DatabaseHelper;
import com.supinfo.a3and.android_project.R;
import com.supinfo.a3and.android_project.Util.Api;
import com.supinfo.a3and.android_project.View.DisplayAllTodoList;
import com.supinfo.a3and.android_project.View.Register;


public class MainActivity extends AppCompatActivity {
    EditText usernameLogin, passwordLogin;
    String dataUsernameLogin, dataPasswordLogin;
    Integer reponse;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        final Button buttonRegister = findViewById(R.id.btnRegister);
        final Button buttonLogin = findViewById(R.id.btnLogin);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                NavigationRegister(v);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getDataLogin();
                initialiseDataLogin();
                Api api = new Api();
                api.login(dataUsernameLogin, dataPasswordLogin);
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (String.valueOf(api.isConnected()).equals("true")){
                    reponse = 1;
                    showToast(reponse);
                    Intent intent = new Intent(v.getContext(), DisplayAllTodoList.class);
                    intent.putExtra("username", dataUsernameLogin);
                    intent.putExtra("password", dataPasswordLogin);
                    startActivity(intent);
                }
                else {
                    reponse = 2;
                    showToast(reponse);
                }
            }
        });
    }

    public void NavigationRegister (View view){
        startActivity(new Intent(this, Register.class));
    }

    public void getDataLogin(){
        usernameLogin = findViewById(R.id.edtUsername);
        passwordLogin = findViewById(R.id.edtPassword);
    }

    public void initialiseDataLogin(){
        dataUsernameLogin = usernameLogin.getText().toString();
        dataPasswordLogin = passwordLogin.getText().toString();
    }

    public void showToast(Integer reponse){
        if (reponse == 1){
            Toast.makeText(this, "Vous êtes maintenant connecté", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Echec de la connexion, veuillez vérifier vos Identifiants.", Toast.LENGTH_SHORT).show();
        }
    }
}
