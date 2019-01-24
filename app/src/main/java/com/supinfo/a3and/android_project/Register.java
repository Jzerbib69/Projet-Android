package com.supinfo.a3and.android_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity{
    EditText userNameText, firstNameText, lastNameText, emailText, passwordText, verifPasswordText;
    Button btnCancel, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnErased:
                Erased();
            case R.id.btnRegister:
                Register();
        }
    }

    public void Erased(){
        EditText UsernameText = findViewById(R.id.edtUsername);
        EditText FirstNameText = findViewById(R.id.edtFirstName);
        EditText LastNameText = findViewById(R.id.edtLastName);
        EditText EmailText = findViewById(R.id.edtEmail);
        EditText PasswordText = findViewById(R.id.edtPassword);
        EditText VerifPasswordText = findViewById(R.id.edtConfirmPassword);
        UsernameText.setText("");
        FirstNameText.setText("");
        LastNameText.setText("");
        EmailText.setText("");
        PasswordText.setText("");
        VerifPasswordText.setText("");
    }

    public void Register(){
        
    }

}
