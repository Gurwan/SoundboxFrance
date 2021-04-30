/*
 * *******************************************************
 * Copyright (c) 2020. Okaria Studio
 * ******************************************************
 */

package automation.test.soundboxfrance;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import automation.test.soundboxfrance.categories.FilmsTVActivity;
import automation.test.soundboxfrance.categories.GeneriquesActivity;
import automation.test.soundboxfrance.categories.JVActivity;
import automation.test.soundboxfrance.categories.MemeActivity;
import automation.test.soundboxfrance.categories.MusicFragment;
import automation.test.soundboxfrance.categories.MusiqueRapAutres;
import automation.test.soundboxfrance.categories.NewsFragment;
import automation.test.soundboxfrance.categories.SportPoliHumActivity;
import automation.test.soundboxfrance.categories.TVActivity;
import automation.test.soundboxfrance.categories.ThemeActivity;
import automation.test.soundboxfrance.categories.YoutubeActivity;

public class MainActivity extends AppCompatActivity {
    String urlprop = "http://play.google.com/store/apps/details?id=automation.test.soundboxfrance";
    String urlpropose = "https://docs.google.com/forms/d/e/1FAIpQLScSp4fVFHqFRI9zNGWaD_Lcn2PJACyLlVHeGr2ASq4rhg1kfg/viewform";

    boolean doubleBackPressed = false;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int pleinEcran = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(pleinEcran, pleinEcran);

        Button buttonFav = findViewById(R.id.button_fav);
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(14);
            }
        });

        Button buttonRecherche = findViewById(R.id.button_search);
        buttonRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(13);
            }
        });

        CardView internetButton = findViewById(R.id.internetButton);
        internetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les mêmes sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(3);
            }
        });

        CardView youtubeButton = findViewById(R.id.youtubeButton);
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les mêmes sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(2);
            }
        });


        CardView sportHumourPolitiqueButton = findViewById(R.id.sporthumourpolitiqueButton);
        sportHumourPolitiqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les sons sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(4);
            }
        });

        CardView musicButton = findViewById(R.id.musictrenteButton);
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les musiques sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(8);
            }
        });

        CardView musicRapButton = findViewById(R.id.musicRapButton);
        musicRapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les musiques sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(9);
            }
        });

        CardView filmsButton = findViewById(R.id.filmButton);
        filmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les films sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(5);
            }
        });

        CardView tvButton = findViewById(R.id.tvButton);
        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les sons sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(6);
            }
        });

        CardView jvButton = findViewById(R.id.jvButton);
        jvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les sons sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(7);
            }
        });

        CardView themeButton = findViewById(R.id.themeButton);
        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les sons sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(11);
            }
        });

        CardView generiquesButton = findViewById(R.id.generiqueButton);
        generiquesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Les génériques sont en train de charger. Patientez quelques secondes", Toast.LENGTH_SHORT).show();
                openA(10);
            }
        });

        CardView nouveautesButton = findViewById(R.id.nouveautesButton);
        nouveautesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(12);
            }
        });

        Button yourSoundboxButton = findViewById(R.id.button_yoursoundbox);
        yourSoundboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(1);
            }
        });

        if((getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},80);
        }

        if(!Settings.System.canWrite(getApplicationContext())){
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }

    }

    public void openA(int a){
        Intent intent = null;
        switch(a){
                case 1:
                    intent = new Intent(this, YourSoundboxActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, YoutubeActivity.class);
                    break;
                case 3:
                    intent = new Intent(this, MemeActivity.class);
                    break;
                case 4:
                    intent = new Intent(this, SportPoliHumActivity.class);
                    break;
                case 5:
                    intent = new Intent(this, FilmsTVActivity.class);
                    break;
                case 6:
                    intent = new Intent(this, TVActivity.class);
                    break;
                case 7:
                    intent = new Intent(this, JVActivity.class);
                    break;
                case 8:
                    intent = new Intent(this, MusicFragment.class);
                    break;
                case 9:
                    intent = new Intent(this, MusiqueRapAutres.class);
                    break;
                case 10:
                    intent = new Intent(this, GeneriquesActivity.class);
                    break;
                case 11:
                    intent = new Intent(this, ThemeActivity.class);
                    break;
                case 12:
                    intent = new Intent(this, NewsFragment.class);
                    break;
                case 13:
                    intent = new Intent(this, ActivityToutRecherche.class);
                    break;
                case 14:
                    intent = new Intent(this, FavoriteActivity.class);
                    break;
                case 15:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlpropose));
                    break;
                case 16:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlprop));
                    break;
            }
            startActivity(intent);
            overridePendingTransition(R.anim.slide_right, R.anim.slide_left_2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 80){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Vous pouvez maintenant définir un son en sonnerie !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Vous ne pouvez pas définir un son en sonnerie si vous refusez les permissions !", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed();
        } else {
            doubleBackPressed = true;
            final ConstraintLayout constraintLayout = findViewById(R.id.activity_main);
            Snackbar.make(constraintLayout, getString(R.string.back), Snackbar.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackPressed = false;
                }
            }, 2000);
        }
    }

}

