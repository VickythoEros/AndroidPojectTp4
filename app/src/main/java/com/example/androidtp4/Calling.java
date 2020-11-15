package com.example.androidtp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Calling extends AppCompatActivity {
    private  static final int REQUEST_CALL = 1 ;
    private EditText numero;
    private ImageView btnCall ;
    private Button phoneReturn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        numero = findViewById(R.id.editTextPhone);
        btnCall = findViewById(R.id.call);
        phoneReturn = findViewById(R.id.phoneReturn);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              lancerAppel();
            }


        });


        phoneReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_btn_first,R.anim.slide_btn_second);
            }
        });

    }

    // methode permettant de passer un appel
    private void lancerAppel() {
        String saisieTel = numero.getText().toString();
        if (saisieTel.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Calling.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Calling.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
         else {
            String number = "tel:" + saisieTel;
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
            //intent.setData(Uri.parse(number));
            startActivity(intent);

            }
        }
        else{
            Toast.makeText(Calling.this,"Entrer Un Numéro",Toast.LENGTH_SHORT).show();
        }
    }//fin lancerAppel



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                lancerAppel();
            }else{

            }
        } 
    }

}
