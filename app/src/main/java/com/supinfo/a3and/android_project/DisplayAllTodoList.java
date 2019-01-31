package com.supinfo.a3and.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

public class DisplayAllTodoList extends AppCompatActivity {

    ListView todoListView;
    String username,password;
    Api api = new Api();
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        final Button buttonLogout = findViewById(R.id.btnLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                api.logout();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (String.valueOf(api.isConnected()).equals("true")) {
                    showToast();
                    startActivity(new Intent(DisplayAllTodoList.this, MainActivity.class));
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("username") && intent.hasExtra("password")) {
                username = intent.getStringExtra("username");
                password = intent.getStringExtra("password");
            }
            todoListView = findViewById(R.id.lstTodoList);

            api.listTodoList(username, password);

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayAllTodoList.this,
                    android.R.layout.simple_list_item_1, api.todo);
            todoListView.setAdapter(adapter);

            todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent intent = new Intent(v.getContext(), DisplayOneTodo.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    intent.putExtra("id", api.otherDetail.get(0));
                    intent.putExtra("userinvited", api.otherDetail.get(1));
                    startActivity(intent);
                }
            });
        }
    }

    public void showToast(){
        Toast.makeText(this, "Vous êtes maintenant déconnecté", Toast.LENGTH_SHORT).show();
    }
}
