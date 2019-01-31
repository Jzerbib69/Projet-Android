package com.supinfo.a3and.android_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayOneTodo extends AppCompatActivity {

    String usernameLast,passwordLast,userFriend;
    String id;
    Api api = new Api();

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
            if (intent.hasExtra("username") && intent.hasExtra("password") && intent.hasExtra("id") && intent.hasExtra("userinvited")) {
                usernameLast = intent.getStringExtra("username");
                passwordLast = intent.getStringExtra("password");
                id = intent.getStringExtra("id");
                userFriend = intent.getStringExtra("userinvited");
            }

            TextView txbisShare = findViewById(R.id.txtIsShared);
            if(userFriend.equals("null")){
                txbisShare.setText("Cette TodoList n'a pas encore été partagée !");
            }
            else {
                txbisShare.setText("Bravo ! Cette TodoList est déjà partagée !");
            }
            api.read(usernameLast, passwordLast, id);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (api.detailAlone != null){
                EditText edtTodoAlone = findViewById(R.id.edtDetailTodo);
                edtTodoAlone.setText(api.detailAlone);
            }
            else{
                api.detailAlone = "Enter a value and click on Modify Button";
                EditText edtTodoAlone = findViewById(R.id.edtDetailTodo);
                edtTodoAlone.setText(api.detailAlone);
            }
        }

        final Button buttonUpdate = findViewById(R.id.btnModify);
        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText contentEdt = findViewById(R.id.edtDetailTodo);
                String detailTodo = contentEdt.getText().toString();
                api.updateTodoList(usernameLast,passwordLast,id,detailTodo);
                    Intent intent = new Intent(v.getContext(), DisplayAllTodoList.class);
                    intent.putExtra("username", usernameLast);
                    intent.putExtra("password", passwordLast);
                    startActivity(intent);
            }
        });

        final Button buttonShare = findViewById(R.id.btnShare);
        buttonShare.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final AlertDialog.Builder alertDialog;
                alertDialog = new AlertDialog.Builder(DisplayOneTodo.this, android.R.style.Theme_Material_Dialog_Alert);
                final EditText input = new EditText(DisplayOneTodo.this);
                LinearLayout.LayoutParams layoutAlertBox = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(layoutAlertBox);
                alertDialog.setView(input);
                alertDialog.setTitle("Share users")
                        .setMessage("Entrez l'utilisateur avec qui vous voulez partagé cette todoList !")
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.setCancelable(true);
                            }
                        })
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String edtnewUserFriend = input.getText().toString();
                                api.shareTodoList(usernameLast,passwordLast,edtnewUserFriend );
                                try {
                                    Thread.sleep(800);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (String.valueOf(api.isConnected()).equals("true")){
                                    Intent intent = new Intent(DisplayOneTodo.this, DisplayAllTodoList.class);
                                    intent.putExtra("username", usernameLast);
                                    intent.putExtra("password", passwordLast);
                                    startActivity(intent);
                                }
                                else {
                                    Log.e("Erreur", "Une erreur de partage est survenue");
                                }
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
