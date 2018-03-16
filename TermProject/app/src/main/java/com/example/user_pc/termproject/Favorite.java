package com.example.user_pc.termproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {

    private FavoritesData favoritesData;
    private List<String> favorites;
    private ArrayAdapter<String> mAdapter;
    private ListView lv;

    private ImageButton deleting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_favorite);

        favoritesData = (FavoritesData) getApplication();
        favorites = favoritesData.getFavorites();

        mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                favorites
        );

        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(mAdapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_favorite,null);
        actionBar.setCustomView(mCustomView);
        deleting = (ImageButton) findViewById(R.id.delete);
        deleting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SparseBooleanArray checked = lv.getCheckedItemPositions();
                List<String> checkedData = new ArrayList<String>();
                for(int i=0; i<checked.size(); i++)
                    if (checked.valueAt(i))
                        checkedData.add(favorites.get(checked.keyAt(i)));

                for(int i=0; i<checkedData.size(); i++){
                    favorites.remove(checkedData.get(i));
                    favoritesData.deleteFavorites(checkedData.get(i));
                    refresh(checkedData.get(i));
                }
                lv.clearChoices();
            }
        });
    }
    

    private void refresh(String delete){
        mAdapter.remove(delete);
        mAdapter.notifyDataSetChanged();
    }
}
