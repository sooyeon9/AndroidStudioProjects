package cse4034.lab.lab9;

import java.util.Locale;

import android.app.ListActivity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class Activity_Ex5 extends ListActivity {

	// Entries will be filled from respective resources.
	private String[] planets;
	private int[] diameters;
	private Integer[] resIds;
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main__ex4);
        
		// Get Strings from string-array resource
		planets = getResources().getStringArray(R.array.planets_list);
		// Get ints from integer-array resource
		diameters = getResources().getIntArray(R.array.diameters);
		
		/*
		 * Code below is for getting resource ids from string-array.
		 * DO NOT MODIFY! 
		 */
		TypedArray temp = getResources().obtainTypedArray(R.array.resourceIds);
		resIds = new Integer[temp.length()];
		
		for (int i = 0; i < temp.length(); i++) {
			resIds[i] = temp.getResourceId(i, -1);
		}
		
		// Create Customized Adapter
        CustomAdapter mAdapter = new CustomAdapter(this,  planets ,diameters, resIds);

		setListAdapter(mAdapter);
        // Set Adapter
			// complete
	}

	// Override list item click handler to display image when a list item is clicked.
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l,  v,  position, id);
		// complete
		image = (ImageView) v.findViewById(R.id.imageView_ex4);
		image.setImageResource(resIds[position]);

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
