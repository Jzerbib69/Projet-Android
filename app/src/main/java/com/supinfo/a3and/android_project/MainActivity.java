package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.supinfo.a3and.android_project.Database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    EditText usernameLogin, passwordLogin;
    String dataUsernameLogin, dataPasswordLogin;
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
                onPause();
                NavigationRegister(v);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getDataLogin();
                initialiseDataLogin();
                Api api = new Api();
                api.login(dataUsernameLogin, dataPasswordLogin);
                Register reg = new Register();
                if (api.isConnected()){
                    Toast.makeText(reg.getContext(), "Vous êtes maintenant connecté", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), DisplayAllTodoList.class);
                    intent.putExtra("username", dataUsernameLogin);
                    intent.putExtra("password", dataPasswordLogin);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(reg.getContext(), "Erreur de connexion, vérifiez vos identifiants", Toast.LENGTH_SHORT).show();
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
}
