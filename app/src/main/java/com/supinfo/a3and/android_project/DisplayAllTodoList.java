package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (String.valueOf(api.isConnected()).equals("true")) {
                    showToast();
                    finish();
                }
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
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayAllTodoList.this,
                    android.R.layout.simple_list_item_1, api.todo);
            todoListView.setAdapter(adapter);
        }

        /*final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);*/
    }

    public void showToast(){
        Toast.makeText(this, "Vous êtes maintenant déconnecté", Toast.LENGTH_SHORT).show();
    }
}
