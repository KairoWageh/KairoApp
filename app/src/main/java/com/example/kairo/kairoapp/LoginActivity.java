package com.example.kairo.kairoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText userName, userPassword;
    Button loginButton;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        userName = (EditText) findViewById(R.id.edit_user_name);
        userPassword = (EditText) findViewById(R.id.edit_user_password);
        loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userName.getText().toString().trim().equals("Kairo")
                        && userPassword.getText().toString().trim().equals("123")){
                    next = new Intent(LoginActivity.this, DataActivity.class);
                    startActivity(next);
                }else if(userName.getText().toString().trim().equals("")
                        || userPassword.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "You must enter all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "User name or pasword is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        } );
    }
}
