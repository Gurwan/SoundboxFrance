/*
 * *******************************************************
 * Copyright (c) 2021. Okaria Studio
 * ******************************************************
 */

package automation.test.soundboxfrance.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import automation.test.soundboxfrance.DatabaseHandler;
import automation.test.soundboxfrance.EventHandlerClass;
import automation.test.soundboxfrance.R;
import automation.test.soundboxfrance.SoundboxRecyclerAdapter;
import automation.test.soundboxfrance.model.SoundObject;

public class FavoriteActivity extends AppCompatActivity {
    private static final String LOG_TAG = "FAVORITEACTIVITY";
    ArrayList<SoundObject> favoriteList = new ArrayList<>();
    RecyclerView FavoriteView;
    SoundboxRecyclerAdapter FavoriteAdapter = new SoundboxRecyclerAdapter(favoriteList,this);
    RecyclerView.LayoutManager FavoriteLayoutManager;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    int az = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        int pleinEcran = WindowManager.LayoutParams.FLAG_FULLSCREEN ; getWindow().setFlags(pleinEcran,pleinEcran);

        Button buttonHome = findViewById(R.id.button_home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(1);
            }
        });

        Button buttonRecherche = findViewById(R.id.button_search);
        buttonRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(2);
            }
        });

        Button buttonYourSoundbox = findViewById(R.id.button_yoursoundbox);
        buttonYourSoundbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(3);
            }
        });

        Button buttonSettings = findViewById(R.id.button_setting);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(4);
            }
        });


        Button playlistButton = findViewById(R.id.button_playlist);
        playlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA(5);
            }
        });



        addDataToArrayList();
        FavoriteView = findViewById(R.id.favoriteRecyclerView);
        FavoriteLayoutManager = new GridLayoutManager(this, 3);
        FavoriteView.setLayoutManager(FavoriteLayoutManager);
        FavoriteView.setAdapter(FavoriteAdapter);
    }

    private void openA(int i) {
        Intent intent = null;
        switch(i){
            case 1:
                intent = new Intent(this, MainActivity.class);
                break;
            case 2:
                intent = new Intent(this, ActivityToutRecherche.class);
                break;
            case 3:
                intent = new Intent(this, YourSoundboxActivity.class);
                break;
            case 4:
                intent = new Intent(this,SettingsActivity.class);
                break;
            case 5:
                intent = new Intent(this,ActivityPlaylist.class);
                break;
        }
        startActivity(intent);
        if(i==1){
            overridePendingTransition(R.anim.slide_left, R.anim.slide_right_2);
        } else {
            overridePendingTransition(R.anim.slide_right, R.anim.slide_left_2);
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventHandlerClass.releaseMediaPlayer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.fav_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        if (menuItem.getItemId() == R.id.action_clear) {
            databaseHandler.clearAll(this);
            return true;
        } else if(menuItem.getItemId() == R.id.action_help){
            Toast.makeText(this, "Vous pouvez ajouter un coup de coeur depuis n'importe quel son de l'application en appuyant longtemps dessus et en appuyant sur coup de coeur.", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Pour retirer un coup de coeur il faut appuyer sur Crève coeur en appuyant longtemps sur un son présent ici même.", Toast.LENGTH_LONG).show();
            return true;
        } else if(menuItem.getItemId() == R.id.action_sort){
            if(az!=0){
                Collections.sort(favoriteList, new Comparator<SoundObject>() {
                    @Override
                    public int compare(SoundObject soundObject, SoundObject t1) {
                        return soundObject.getItemName().compareTo(t1.getItemName());
                    }
                });
                az = 0;
            } else {
                Collections.sort(favoriteList, Collections.reverseOrder(new Comparator<SoundObject>() {
                    @Override
                    public int compare(SoundObject soundObject, SoundObject t1) {
                        return soundObject.getItemName().compareTo(t1.getItemName());
                    }
                }));
                az = 1;
            }
            FavoriteAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void addDataToArrayList(){
        favoriteList.clear();

        Cursor cursor = databaseHandler.getFavorites();
        if (cursor.getCount() == 0){
            Log.e(LOG_TAG, "Cursor is empty or failed to convert data");
            cursor.close();
        }

        if (cursor.getCount() != favoriteList.size() ){
            while (cursor.moveToNext() ){
                String name = cursor.getString(cursor.getColumnIndex("favoName"));
                Integer id = cursor.getInt(cursor.getColumnIndex("favoId"));
                String img = cursor.getString(cursor.getColumnIndex("favoImage"));
                int imageR = getResources().getIdentifier(img,"drawable", getPackageName());
                favoriteList.add(new SoundObject(name, id, imageR));
                FavoriteAdapter.notifyDataSetChanged();
            }
            cursor.close();
        }
    }
}
