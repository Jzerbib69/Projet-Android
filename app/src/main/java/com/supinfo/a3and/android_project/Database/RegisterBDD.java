package com.supinfo.a3and.android_project.Database;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import com.supinfo.a3and.android_project.R;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RegisterBDD extends AppCompatActivity {

    DatabaseHelper dbHelper = new DatabaseHelper(this);

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
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put(dbHelper.COLUMN_USERNAME, dataUsername);
        contentValues.put(dbHelper.COLUMN_FIRSTNAME, dataFirstName);
        contentValues.put(dbHelper.COLUMN_LASTNAME, dataLastName);
        contentValues.put(dbHelper.COLUMN_EMAIL, dataEmail);
        contentValues.put(dbHelper.COLUMN_PASSWORD, dataPassword);
        db.insert(dbHelper.TABLE_USERS, null, contentValues);

    }



}
