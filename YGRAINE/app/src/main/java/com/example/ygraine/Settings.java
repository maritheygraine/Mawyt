package com.example.ygraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    int backpress= 1;
    Button btnBack,btnDelete;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        db = new DatabaseHelper(this);
        btnDelete = (Button) findViewById(R.id.delete);
        btnBack = (Button) findViewById(R.id.back);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        final String value = sharedPreferences.getString("USER","");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //db.deleteAccount(value);
                boolean status = db.deleteAccount(value);
                if(status == true){
                    Toast.makeText(getApplicationContext(),"Successfully Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Unable to delete account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
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
