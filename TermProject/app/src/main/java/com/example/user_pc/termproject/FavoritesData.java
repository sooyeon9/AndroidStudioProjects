package com.example.user_pc.termproject;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user-pc on 2016-06-19.
 */
public class FavoritesData extends Application {
    private List<String> favorites = new ArrayList<String>();

    private String[] japan;

    public List<String> getFavorites()
    {
        japan = getResources().getStringArray(R.array.japan);
        for(int i=0; i<japan.length; i++)
            favorites.add(japan[i]);

        /*favorites.add("빨강");
        favorites.add("주황");
        favorites.add("노랑");
        favorites.add("초록");
        favorites.add("파랑");
        favorites.add("남색");
        favorites.add("보라");*/
        return favorites;
    }

    public void addFavorites(String add){
        boolean exist = false;
        for(int i=0; i<favorites.size(); i++)
            if(favorites.get(i).equals(add)){
                Toast.makeText(this, "이미 즐겨찾기에 존재합니다!", Toast.LENGTH_SHORT).show();
                exist = true;
            }
        if(!exist)
            this.favorites.add(add);
    }

    public void deleteFavorites(String delete){
        this.favorites.remove(delete);
    }
}
