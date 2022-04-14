package com.info.ilkoyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SonucEkraniActivity extends AppCompatActivity {
private TextView textViewToplamSkor;
private TextView textViewEnYuksekSkor;
private Button buttonTekrarDene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonuc_ekrani);
        textViewEnYuksekSkor = findViewById(R.id.textViewEnYuksekSkor);
        textViewToplamSkor = findViewById(R.id.textViewToplamSkor);
        buttonTekrarDene =findViewById(R.id.buttonTekrarDene);

        int skor =getIntent().getIntExtra("skor",0);
        textViewToplamSkor.setText("SKOR : "+String.valueOf(skor));

        SharedPreferences sp = getSharedPreferences("Sonuc", Context.MODE_PRIVATE);
        int enYuksekSkor = sp.getInt("EnYuksekSkor",0);

        if(skor> enYuksekSkor){
SharedPreferences.Editor editor =sp.edit();
editor.putInt("EnYuksekSkor",skor);
editor.commit();
            textViewEnYuksekSkor.setText(String.valueOf(enYuksekSkor));

        }else{
textViewEnYuksekSkor.setText(String.valueOf(enYuksekSkor));
        }

        buttonTekrarDene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(SonucEkraniActivity.this,OyunEkraniActivity.class));
finish();




            }
        });

    }
}
