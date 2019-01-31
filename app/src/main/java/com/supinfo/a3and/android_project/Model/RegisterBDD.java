package com.supinfo.a3and.android_project.Model;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import com.supinfo.a3and.android_project.R;


public class RegisterBDD extends AppCompatActivity {

    EditText usernameText, firstNameText, lastNameText,emailText, passwordText;
    String dataUsername, dataFirstName, dataLastName, dataEmail, dataPassword;

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

    public void RegisterBDD(){
        getData();
        initialiseData();
    }



}
