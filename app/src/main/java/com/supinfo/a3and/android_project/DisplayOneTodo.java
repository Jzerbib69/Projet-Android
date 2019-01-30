package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DisplayOneTodo extends AppCompatActivity {

    String usernameLast,passwordLast;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onetodo);

        final Button buttonBack = findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), DisplayAllTodoList.class);
                intent.putExtra("username", usernameLast);
                intent.putExtra("password", passwordLast);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("username") && intent.hasExtra("password")) {
                usernameLast = intent.getStringExtra("username");
                passwordLast = intent.getStringExtra("password");
                id = intent.getIntExtra("id");
                Log.e("username Last ", usernameLast);
                Log.e("password Last ", passwordLast);
            }
            Api api = new Api();
            api.readListTodo(usernameLast, passwordLast, id);
        }
    }
}
