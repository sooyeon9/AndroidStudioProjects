package cse4034.lab.lab9;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

public class Activity_Ex4 extends ListActivity {

	private static final String TAG="SPARSE";
	
	// Entries will be filled from the respective resources.
	private String[] planets;
	private int[] diameters;
	private ListView listView;
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main__ex4);
        
		// Get Strings from string-array resource
		planets = getResources().getStringArray(R.array.planets_list); // complete
	    // Get ints from integer-array resource
		diameters = getResources().getIntArray(R.array.diameters); // complete
		/*
		 *  Create an ArrayAdapter<String> adapter
		 *  Use "android.R.layout.simple_list_item_multiple_choice" and "android.R.id.text1"
		 */
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_multiple_choice,
				android.R.id.text1,
				planets
		);

		setListAdapter(mAdapter);
		listView = getListView();
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        // Set Adapter
			// complete

        // Set multiple choice mode
			// complete
	}

	
	public void onClickHandler(View v) {
		// complete
		textview = (TextView) v.findViewById(R.id.textView_ex3);
		int sum = 0;
		SparseBooleanArray checked = getListView().getCheckedItemPositions();
		for(int i=0; i<checked.size(); i++) {
			if (checked.valueAt(i) == true)
				sum += diameters[i];
		}
		textview.setText(sum+" KM");
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
