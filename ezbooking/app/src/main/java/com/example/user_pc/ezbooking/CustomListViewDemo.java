package com.example.user_pc.ezbooking;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class CustomListViewDemo extends ListActivity {

    private ArrayList<String> name = new ArrayList<>();
    private List<String> address = new ArrayList<>();
    private List<String> prices = new ArrayList<>();
    private List<String> images = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_selectlist);

        name.add("1");
        name.add("2");

        setListAdapter(new CustomAdapter(this, name, address, prices,images));
    }
}
