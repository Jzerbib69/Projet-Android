package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayAllTodoList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        Intent intent = getIntent();
        if (intent != null){
            String username = "";
            String password= "";
            if (intent.hasExtra("username") && intent.hasExtra("password")){
                username = intent.getStringExtra("username");
                password = intent.getStringExtra("username");
            }
            TextView textView = findViewById(R.id.txtView);
            textView.setText(username + "; " + password);
        }
    }
}
