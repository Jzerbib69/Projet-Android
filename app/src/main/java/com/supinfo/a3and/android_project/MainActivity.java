package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    EditText usernameLogin, passwordLogin;
    String dataUsernameLogin, dataPasswordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                LinearLayout layoutParent = findViewById(R.id.linearLayoutParent);
                layoutParent.setVisibility(LinearLayout.GONE);
                ListView listViewTODO = findViewById(R.id.ltvTodoList);
                listViewTODO.setVisibility(ListView.VISIBLE);
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

    /*public void NavigationDisplay (View view){
        startActivity(new Intent(this, Display.class));
    }*/
}
