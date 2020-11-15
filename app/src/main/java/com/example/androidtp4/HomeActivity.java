package com.example.androidtp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageView tel ;
    private ImageView sms ;
    private ImageView video ;
    private ImageView audio ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        //affectation des valeur
        tel = findViewById(R.id.telephone);
        sms = findViewById(R.id.sms);
        video = findViewById(R.id.video);
        audio = findViewById(R.id.audio);

        // telephone transition controle
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Calling.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_first,R.anim.slide_second);
            }
        });

        // sms transition controle
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Sms.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_first,R.anim.slide_second);
            }
        });


        // video transition controle
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Video.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_first,R.anim.slide_second);
            }
        });


        // music transition controle
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Audio.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_first,R.anim.slide_second);
            }
        });
    }
}