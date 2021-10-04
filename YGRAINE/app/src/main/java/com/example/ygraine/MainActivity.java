package com.example.ygraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation anim_buttom;
    TextView app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        anim_buttom = AnimationUtils.loadAnimation(this, R.anim.buttom_animation);
        app = (TextView) findViewById(R.id.app_name);
        app.setAnimation(anim_buttom);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent = new Intent(MainActivity.this, Login.class);
                 startActivity(intent);
                 finish();
             }
         }, 4100);



    }
}
