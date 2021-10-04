package com.example.ygraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText username, password;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = (Button) findViewById(R.id.button_login);
        btnRegister = (Button) findViewById(R.id.button_register);
        username = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                Boolean CheckUsernamePassword = db.UsernamePassword(strUsername,strPassword);

                if(CheckUsernamePassword==true){
                    Toast.makeText(getApplicationContext(),"Succesfully Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Main_Screen.class);

                    SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("USER", strUsername);
                    editor.apply();

                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(),"Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }



}
