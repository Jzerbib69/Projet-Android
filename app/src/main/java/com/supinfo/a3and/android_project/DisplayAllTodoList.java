package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayAllTodoList extends AppCompatActivity {

    ListView todoListView;
    String userFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        final Button buttonLogout = findViewById(R.id.btnLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Api api = new Api();
                api.logout();
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String username = "";
            String password = "";
            if (intent.hasExtra("username") && intent.hasExtra("password")) {
                username = intent.getStringExtra("username");
                password = intent.getStringExtra("password");
            }
            TextView textView = findViewById(R.id.txtView);
            textView.setText(username + "; " + password);


            todoListView = (ListView) findViewById(R.id.lstTodoList);

            Api api = new Api();
            api.listTodoList(username, password);
        }

        /*final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);*/
    }
}
