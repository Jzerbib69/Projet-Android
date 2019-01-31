package com.supinfo.a3and.android_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DisplayOneTodo extends AppCompatActivity {

    String usernameLast,passwordLast,userFriend;
    String id;
    Api api = new Api();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onetodo);

        LayoutInflater factory = LayoutInflater.from(DisplayOneTodo.this);
        //final View alertDialogView = factory.inflate(R.layout., null);

        final Button buttonShare = findViewById(R.id.btnShare);
        buttonShare.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(DisplayOneTodo.this, android.R.style.Theme_Material_Dialog_Alert);
                builder.setTitle("Share users")
                        .setMessage("Entrez l'utilisateur avec qui vous voulez partagé ce todo !")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                //api.shareTodoList(usernameLast,passwordLast, );
            }
        });

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
            if (intent.hasExtra("username") && intent.hasExtra("password") && intent.hasExtra("id") && intent.hasExtra("userinvited")) {
                usernameLast = intent.getStringExtra("username");
                passwordLast = intent.getStringExtra("password");
                id = intent.getStringExtra("id");
                userFriend = intent.getStringExtra("userinvited");
                Log.e("username", usernameLast);
                Log.e("password", passwordLast);
                Log.e("id", id);
                Log.e("userFriend", userFriend);
            }
            api.read(usernameLast, passwordLast, id);
            EditText edtTodoAlone = findViewById(R.id.edtDetailTodo);
            Log.e("test", api.detailAlone);
            //edtTodoAlone.setText(api.todoAlone);
        }

        final Button buttonUpdate = findViewById(R.id.btnModify);
        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                /*EditText contentEdt = findViewById(R.id.edtDetailTodo);
                String detailTodo = contentEdt.getText().toString();
                api.updateTodoList(usernameLast,passwordLast,id,detailTodo);*/
                Intent intent = new Intent(v.getContext(), DisplayAllTodoList.class);
                intent.putExtra("username", usernameLast);
                intent.putExtra("password", passwordLast);
                startActivity(intent);
            }
        });
    }
}
