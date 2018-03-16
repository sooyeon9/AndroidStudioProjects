package cse4034.lab.lab9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class CustomAdapter extends BaseAdapter {
	
	private Context mContext;
	private String[] mPlanets;
	private int[] mDiameters;
	private Integer[] mThumbIds;
	private LayoutInflater mInflater;


	public CustomAdapter(Context context, String[] planets, int[] diams, Integer[] ids){
		mContext = context;
		mPlanets = planets;
		mDiameters = diams;
		mThumbIds = ids;
		
		// Get LayoutInflater to inflate item layout later.
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() { return mThumbIds.length; }
	
	@Override
	public Object getItem(int position) { return null;	}

	@Override
	public long getItemId(int position) { return position;	}

	@Override
	// create a new View for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		/*
		 * First, try a naive version that do not use the "convertView".
		 * If you succeed, the modify this method so that "convertView" is utilized.
		 * Finally, if you succeed again, try the ViewHolder Pattern.
		 */
		if(convertView == null)
			convertView = mInflater.inflate(R.layout.item_layout,parent,false);
		((TextView) convertView.findViewById(R.id.name)).setText(mPlanets[position]);
		((TextView) convertView.findViewById(R.id.diameter)).setText(mDiameters[position]);
		((ImageView) convertView.findViewById(R.id.image)).setImageResource(mThumbIds[position]);

		// complete
		return convertView;
	}
}