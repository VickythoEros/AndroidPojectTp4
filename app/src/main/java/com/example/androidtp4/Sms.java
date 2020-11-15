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
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Sms extends AppCompatActivity {

    // declaration des methodes
    private  static final int REQUEST_SMS = 1 ;
    private EditText numeroPhoneSms,smsField;
    private Button phoneReturnSms ;
    private ImageButton sendBtnSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        // affectation des valeurs
        phoneReturnSms = findViewById(R.id.phoneReturnSms);
        numeroPhoneSms = findViewById(R.id.numeroPhoneSms);
        smsField = findViewById(R.id.smsField);
        sendBtnSms = findViewById(R.id.sendBtnSms);

        // ecoute de l'evenement click sur le button ENVOYER
        sendBtnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });


        // controle de button de retour
        phoneReturnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_btn_first,R.anim.slide_btn_second);
            }
        });

    }


        private void sendSms() {

            String numeroTel = numeroPhoneSms.getText().toString();
            String SMS = smsField.getText().toString();


            if (numeroTel.trim().length() == 0){
                Toast.makeText(Sms.this,"Entrer Un Numéro",Toast.LENGTH_SHORT).show();
            }
            else{

                if (SMS.trim().length()  == 0){
                    Toast.makeText(Sms.this,"Entrer Un Message",Toast.LENGTH_SHORT).show();
                }
                else{

                    if (ContextCompat.checkSelfPermission(Sms.this,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Sms.this,
                                new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
                    }
                    else {

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(numeroTel, null, SMS, null, null);
                        smsField.setText("");
                        numeroPhoneSms.setText("");
                        Toast.makeText(Sms.this,"Message Envoyé Avec Succes",Toast.LENGTH_SHORT).show();


                    }

                }
            }


          /*  if (numeroTel.trim().length() > 0 && SMS.trim().length() >0  ) {
                if (ContextCompat.checkSelfPermission(Sms.this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Sms.this,
                            new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
                }
                else {

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(numeroTel, null, SMS, null, null);



                }
            }
            else{
                Toast.makeText(Sms.this,"Entrer Un Numéro",Toast.LENGTH_SHORT).show();
            }*/


    }// fin


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendSms();
            }else{

            }
        }
    }
}