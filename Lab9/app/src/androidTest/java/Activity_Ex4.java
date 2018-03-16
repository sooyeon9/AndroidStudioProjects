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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main__ex3);
        
		// Get Strings from string-array resource
		planets = ...; // complete
	    // Get ints from integer-array resource
		diameters = ...; // complete
		
		/*
		 *  Create an ArrayAdapter<String> adapter
		 *  Use "android.R.layout.simple_list_item_multiple_choice" and "android.R.id.text1"
		 */
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
				// complete
		);
        
        // Set Adapter
			// complete

        // Set multiple choice mode
			// complete
	}

	
	public void onClickHandler(View v) {
		// complete
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
