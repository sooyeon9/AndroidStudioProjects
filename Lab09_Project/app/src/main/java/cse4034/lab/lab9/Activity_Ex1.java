package cse4034.lab.lab9;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Activity_Ex1 extends Activity {

    // Entries will be filled from the string-array resource.
    private String[] planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__ex1);

        ListView mListView = (ListView) findViewById(R.id.list_ex1);

        // Get Strings from the string-array resource "planets_list"
        planets = getResources().getStringArray(R.array.planets_list); // complete

		/*
         *  Create an ArrayAdapter<String> adapter
		 *  Use "android.R.layout.simple_list_item_1" and "android.R.id.text1"
		 */
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                planets
        );

        mListView.setAdapter(mAdapter);

        // Set Adapter
            // complete

        // Register OnItemClickListener to display image when a list item is clicked.
        mListView.setOnItemClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> aV, View v, int position, long id) {

                        // One possible way to get resource id from a string name.
                        // For example, you can get "R.drawable.sun" for "sun.png" from the string name "Sun".
                        // DO NOT MODIFY THIS LINE!!
                        int imageId = getResources().getIdentifier(planets[position].toLowerCase(Locale.getDefault()),
                                "drawable", Activity_Ex1.this.getPackageName());

                        ((ImageView) findViewById(R.id.imageView_ex0)).setImageResource(imageId);
                    }
                });
    }

    /*
     * Ignore below....
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
