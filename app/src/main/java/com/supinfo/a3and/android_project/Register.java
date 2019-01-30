package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity{
    EditText usernameText, firstNameText, lastNameText,emailText, passwordText;
    String dataUsername, dataFirstName, dataLastName, dataEmail, dataPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnErased:
                Erased();
                break;
            case R.id.btnRegister:
                Register();
        }
    }

    public void getData(){
        usernameText = findViewById(R.id.edtUsernameRegister);
        firstNameText = findViewById(R.id.edtFirstName);
        lastNameText = findViewById(R.id.edtLastName);
        emailText = findViewById(R.id.edtEmail);
        passwordText = findViewById(R.id.edtPasswordRegister);
    }

    public void initialiseData(){
        dataUsername = usernameText.getText().toString();
        dataFirstName = firstNameText.getText().toString();
        dataLastName = lastNameText.getText().toString();
        dataEmail = emailText.getText().toString();
        dataPassword = passwordText.getText().toString();
    }

    public void Erased(){
        getData();
        usernameText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        emailText.setText("");
        passwordText.setText("");
    }

    public void Register(){
        getData();
        initialiseData();
        if (dataUsername.matches("") || dataFirstName.matches("") || dataLastName.matches("") || dataEmail.matches("")
                || dataPassword.matches(""))
        {
            Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
        }
        else {
            Api api = new Api();
            api.register(dataUsername, dataPassword, dataFirstName, dataLastName, dataEmail, this);
            Intent intent = new Intent(this, DisplayAllTodoList.class);
            intent.putExtra("username", dataUsername);
            intent.putExtra("password", dataPassword);
            startActivity(intent);
            Toast.makeText(this, "Vous êtes maintenant connecté", Toast.LENGTH_SHORT).show();
        }
    }
}
