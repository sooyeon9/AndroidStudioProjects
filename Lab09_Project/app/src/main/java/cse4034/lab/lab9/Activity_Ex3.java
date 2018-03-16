package cse4034.lab.lab9;

import java.util.Locale;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Ex3 extends ListActivity {

	// Entries will be filled from the string-array resource.
	private String[] planets;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main__ex3);

		// Get Strings from the string-array resource "planets_list"
		planets = getResources().getStringArray(R.array.planets_list); // complete

		/*
		 *  Create an ArrayAdapter<String> adapter
		 *  Use "android.R.layout.simple_list_item_single_choice" and "android.R.id.text1"
		 */
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				planets
		);

		setListAdapter(mAdapter);
		listView = getListView();
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set Adapter
			// complete

        // Set single choice mode
			// complete
	}

	// Override list item click handler to display image when a list item is clicked.
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l,  v,  position, id);
		// Toast(....) complete
		String text = "item "+position+"= "+planets[position]+" selected!";
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}
	
	public void onClickHandler(View v) {
		int imageId = getResources().getIdentifier(planets[listView.getCheckedItemPosition()].toLowerCase(Locale.getDefault()),
				"drawable", this.getPackageName());
		((ImageView) findViewById(R.id.imageView_ex2)).setImageResource(imageId);
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
