package com.example.user_pc.lab06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList;

    public Adapter(){
        this.listViewItemList = new ArrayList<ListViewItem>();
    }
    @Override
    public int getCount(){return listViewItemList.size();}
    @Override
    public String getItem(int position){return listViewItemList.get(position).getName();}
    @Override
    public long getItemId(int position){return position;}
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sql_problem2_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.list_name);
        TextView tel = (TextView) convertView.findViewById(R.id.list_tel);

        ListViewItem listViewItem = listViewItemList.get(position);
        name.setText(listViewItem.getName());
        tel.setText(listViewItem.getTel());

        return convertView;
    }
    // ListViewItem 의 layout은 sql_problem2_item 입니다
    // TextView 2개로 되어있는데, 이는 ListView에 각 아이템(연락처)를 나타낼 때 각각은 name과 tel을 나타냅니다.
    // 그래서 addItem은 name, tel 만 인수로 넣어줍니다.

    public void addItem(String name, String tel) {
        ListViewItem item = new ListViewItem();

        item.setName(name);
        item.setTel(tel);

        listViewItemList.add(item);
    }
}