package com.viatorfortis.guideme.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

//import com.viatorfortis.guideme.BuildConfig;
import com.viatorfortis.guideme.R;

public class MainActivity extends AppCompatActivity {


    private ImageView searchByNameModeImageView;
    private ImageView nearestObjectsModeImageView;
    private ImageView selectOnMapModeImageView;
    private ImageView favouritesModeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appBar = findViewById(R.id.tb_search_by_name);
        setSupportActionBar(appBar);
        //getSupportActionBar().setTitle(this.getApplicationInfo().name);

        searchByNameModeImageView = findViewById(R.id.iv_search_by_name_mode);
        searchByNameModeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearchByNameModeActivity();
            }
        });

        nearestObjectsModeImageView = findViewById(R.id.iv_nearest_objects_mode);
        nearestObjectsModeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nearestObjectsModeActivity();
            }
        });

        selectOnMapModeImageView = findViewById(R.id.iv_select_on_map_mode);
        selectOnMapModeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectOnMapModeActivity();
            }
        });

        favouritesModeImageView = findViewById(R.id.iv_favourites_mode);
        favouritesModeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouritesModeActivity();
            }
        });
    }

    private void startSearchByNameModeActivity () {

        //Toast.makeText(MainActivity.this,"BuildConfig.iziTravelApiKey " + BuildConfig.iziTravelApiKey, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, SearchByNameModeActivity.class);
        startActivity(intent);

        //Toast.makeText(MainActivity.this,"start \"SearchByName\" mode", Toast.LENGTH_LONG).show();
    }

    private void nearestObjectsModeActivity () {
        Toast.makeText(MainActivity.this,"start \"NearestObjects\" mode", Toast.LENGTH_LONG).show();
    }

    private void selectOnMapModeActivity () {
        Toast.makeText(MainActivity.this,"start \"SelectOnMap\" mode", Toast.LENGTH_LONG).show();
    }

    private void favouritesModeActivity () {
        Toast.makeText(MainActivity.this,"start \"Favourites\" mode", Toast.LENGTH_LONG).show();
    }
}
