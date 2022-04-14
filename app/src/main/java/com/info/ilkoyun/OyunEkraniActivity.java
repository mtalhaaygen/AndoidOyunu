package com.info.ilkoyun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Timer;
import java.util.TimerTask;

public class OyunEkraniActivity extends AppCompatActivity {
    private ConstraintLayout CL;
    private TextView textViewSKOR;
    private TextView textViewOyunaBasla;
    private ImageView pembe;
    private ImageView siyah;
    private ImageView yesil;
    private ImageView AnaKarakter;



    private int ekrangenisligi ;
    private int ekranyuksekligi ;
    private int anakarakteryuksekligi ;
    private int anakaraktergenisligi ;



    private int AnaKarakterX;
    private int AnaKarakterY;
    private int pembeX;
    private int pembeY;
    private int siyahY;
    private int siyahX;
    private int yesilX;
    private int yesilY;



    private boolean dokunmaKontrol = false;
    private boolean baslangicKontrol = false;


    private int pembeHiz;
    private int siyahHiz;
    private int yesilHız;
    private int anakarakterHiz;



    private Timer time = new Timer();
    private Handler handler = new Handler();



    private int skor =0 ;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2342991069772627/9371092671");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        CL = findViewById(R.id.CL);
        textViewSKOR = findViewById(R.id.textViewSKOR);
        textViewOyunaBasla = findViewById(R.id.textViewOyunaBasla);
        pembe = findViewById(R.id.pembe);
        siyah = findViewById(R.id.siyah);
        yesil = findViewById(R.id.yesil);
        AnaKarakter = findViewById(R.id.AnaKarakter);

        //Cisimleri ekranın dışına çıkarma
        pembe.setY(-80);
        pembe.setX(-80);
        siyah.setY(-80);
        siyah.setX(-80);
        yesil.setY(-80);
        yesil.setX(-80);


CL.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(baslangicKontrol){
            if( event.getAction() == MotionEvent.ACTION_DOWN){
                Log.e("MotionEvent","ekrana dokunuldu");
                dokunmaKontrol = true ;
            }
            if( event.getAction() == MotionEvent.ACTION_UP){
                Log.e("MotionEvent","ekranı bıraktı");
                dokunmaKontrol = false ;

            }

        }else{
            baslangicKontrol = true ;
            textViewOyunaBasla.setVisibility(View.INVISIBLE);
            AnaKarakterX= (int) AnaKarakter.getX();
            AnaKarakterY= (int) AnaKarakter.getY();

ekrangenisligi = CL.getWidth();
ekranyuksekligi = CL.getHeight();
anakaraktergenisligi = AnaKarakter.getWidth();
anakarakteryuksekligi = AnaKarakter.getHeight();


            time.schedule(new TimerTask() {
                @Override
                public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                     AnakarakterHareketEttirme();
                        cisimleriHaraketEttirme();
                        carpısmaKontrol();
                    }
                });
                }
            },0,20);

        }

        return true;
    }
});


    }
    public void AnakarakterHareketEttirme(){
        anakarakterHiz = Math.round(ekranyuksekligi/60);
        if(dokunmaKontrol){
            AnaKarakterY -=anakarakterHiz;
        }
        else{
            AnaKarakterY +=anakarakterHiz;
        }




        if(AnaKarakterY <=0){
            AnaKarakterY = 0;
        }
        if (AnaKarakterY >=  (CL.getHeight() - AnaKarakter.getHeight())){
            AnaKarakterY = (CL.getHeight() - AnaKarakter.getHeight());
        }



        AnaKarakter.setY(AnaKarakterY);
    }


    public void cisimleriHaraketEttirme(){
        /////////////////////////////////////////////////////////
        yesilHız = Math.round(ekrangenisligi/60);
        siyahHiz = Math.round(ekrangenisligi/45);
        pembeHiz = Math.round(ekrangenisligi/38);

        siyahX -=siyahHiz;
       if(siyahX < 0){
           siyahX = ekrangenisligi +20;
           siyahY = (int) Math.floor(Math.random() * (ekranyuksekligi));
       }
       siyah.setX(siyahX);
       siyah.setY(siyahY);

////////////////////////////////////////////////////////////////
        pembeX -=pembeHiz;
        if(pembeX < 0){
            pembeX = ekrangenisligi +20;
            pembeY = (int) Math.floor(Math.random() *(ekranyuksekligi));
        }
        pembe.setX(pembeX);
        pembe.setY(pembeY);

////////////////////////////////////////////////////////////////

        yesilX -=yesilHız;
        if(yesilX < 0){
            yesilX = ekrangenisligi +20;
            yesilY = (int) Math.floor(Math.random() * (ekranyuksekligi));
        }
        yesil.setX(yesilX);
        yesil.setY(yesilY);
    }


    public void carpısmaKontrol(){

        int yesilMerkezX = yesilX + yesil.getWidth()/2;
        int yesilhMerkezY = yesilY + yesil.getHeight()/2;

        if( 0<= yesilMerkezX && yesilMerkezX <= anakaraktergenisligi
                && AnaKarakterY <= yesilhMerkezY && yesilhMerkezY<=AnaKarakterY+anakarakteryuksekligi){
            skor += 20;
            yesilX =-10;

        }


        int pembeMerkezX = pembeX + pembe.getWidth()/2;
        int pembeMerkezY = pembeY + pembe.getHeight()/2;

        if( 0<= pembeMerkezX && pembeMerkezX <= anakaraktergenisligi
                && AnaKarakterY <= pembeMerkezY && pembeMerkezY<=AnaKarakterY+anakarakteryuksekligi){
skor += 50;
pembeX =-10;

        }




        int siyahMerkezX = siyahX + siyah.getWidth()/2;
        int siyahMerkezY = siyahY + siyah.getHeight()/2;

        if( 0<= siyahMerkezX && siyahMerkezX <= anakaraktergenisligi
                && AnaKarakterY <= siyahMerkezY && siyahMerkezY<=AnaKarakterY+anakarakteryuksekligi){

siyahX =-10;
time.cancel();
time = null;

            Intent intent =new Intent(OyunEkraniActivity.this,SonucEkraniActivity.class);
        intent.putExtra("skor",skor);
        finish();
        startActivity(intent);
        mInterstitialAd.show();
        }




        textViewSKOR.setText("SKOR : "+String.valueOf(skor));

    }


}
