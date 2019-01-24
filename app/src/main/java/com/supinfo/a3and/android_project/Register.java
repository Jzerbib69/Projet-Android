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
            case R.id.btnCancel:
                userNameText = (EditText) findViewById(R.id.edtUsername);
                firstNameText = (EditText) findViewById(R.id.edtFirstName);
                lastNameText = (EditText) findViewById(R.id.edtLastName);
                emailText = (EditText) findViewById(R.id.edtEmail);
                passwordText = (EditText) findViewById(R.id.edtPassword);
                verifPasswordText = (EditText) findViewById(R.id.edtConfirmPassword);
                userNameText.setText("");
                firstNameText.setText("");
                lastNameText.setText("");
                emailText.setText("");
                passwordText.setText("");
                verifPasswordText.setText("");
            case R.id.btnRegister:
                /*
                */
        }
    }

}
