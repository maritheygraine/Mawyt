package com.example.ygraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText et1, et2, et3, et4,et5;
    DatabaseHelper db;
    Button btnBack;
    int backpress = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        et1 = (EditText) findViewById(R.id.et_new_name);
        et2 = (EditText) findViewById(R.id.et_new_email);
        btnBack = (Button) findViewById(R.id.back_button);
        et3 = (EditText) findViewById(R.id.et_new_username);
        et4 = (EditText) findViewById(R.id.et_new_password);
        et5 = (EditText) findViewById(R.id.et_new_password2);


        Button btnRegister = (Button) findViewById(R.id.button_signup);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = et1.getText().toString(); //name
                String s2 = et2.getText().toString(); //email
                String s3 = et3.getText().toString(); //username
                String s4 = et4.getText().toString(); //password
                String s5 = et5.getText().toString(); //confirm password

                if(s1.equals("") || s2.equals("") || s3.equals("") ||s4.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                } else {
                    if(s4.equals(s5)) {
                        Boolean checkUserName = db.checkUserName(s3);
                        if (checkUserName == true) {
                            Boolean insert = db.insert(s3, s4,s1,s2);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Username Already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void onBackPressed(){
        backpress = (backpress + 1);


        if (backpress>1) {
            this.finish();
        }
    }
}
