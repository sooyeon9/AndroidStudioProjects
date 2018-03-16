package com.example.user_pc.termproject;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Random extends AppCompatActivity {

    private TextView tv;
    private FavoritesData favoritesData;
    private List<String> favorites;

    private ImageButton retrying;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_random);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_random,null);
        actionBar.setCustomView(mCustomView);
        retrying = (ImageButton) findViewById(R.id.retry);
        retrying.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                tv.setText(randomPick());
            }
        });

        favoritesData = (FavoritesData) getApplication();
        favorites = favoritesData.getFavorites();

        tv = (TextView) findViewById(R.id.textView);
        tv.setText(randomPick());
    }


    public String randomPick(){
        int random =(int) (Math.random() * favorites.size());
        return favorites.get(random);
    }


}
