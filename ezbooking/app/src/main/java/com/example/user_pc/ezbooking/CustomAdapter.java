package com.example.user_pc.ezbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CustomAdapter extends BaseAdapter {
    private Context mcontext;
    private List<String> mname;
    private List<String> maddress;
    private List<String> mprices;
    private List<Integer> mimages = new ArrayList<>();
    private LayoutInflater minflater;


    public CustomAdapter(Context context, List<String> name, List<String> address, List<String> prices, List<String> ids){
        mcontext = context;
        mname = name;
        maddress = address;
        mprices = prices;
        for(int i=0; i<ids.size(); i++){
            String resName = "@drawable/"+ids.get(i);
            String packName = context.getPackageName();
            int resID = context.getResources().getIdentifier(resName,"drawable",packName);
            mimages.add(resID);
        }
        minflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount(){return  mimages.size();}
    public Object getItem(int position){return null;}
    public long getItemId(int position){return position;}

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null)
            convertView = minflater.inflate(R.layout.layout_selectlist_item,parent,false);

        ((ImageView) convertView.findViewById(R.id.list_image)).setImageResource(mimages.get(position));
        ((TextView) convertView.findViewById(R.id.name)).setText(mname.get(position));
        ((TextView) convertView.findViewById(R.id.address)).setText(maddress.get(position));
        ((TextView) convertView.findViewById(R.id.prices)).setText(mprices.get(position));

        return convertView;

    }
}
