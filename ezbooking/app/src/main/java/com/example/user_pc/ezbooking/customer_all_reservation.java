package com.example.user_pc.ezbooking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ksy on 16. 8. 23..
 */
public class customer_all_reservation extends AppCompatActivity {

    SQLiteDatabase db;
    DBhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ListView listView;

        // simple adapter test
        ArrayList<HashMap<String, String>> mlist = new ArrayList<HashMap<String, String>> ();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_all_reservation);


        //임시
        String c_id = "y123456";

        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }


        Log.d("DD", "SELECT");

        Cursor c = db.rawQuery("SELECT num,date FROM reservation where customer_id= ? ", new String[]{c_id});
        String date = "";
        String num = "";
        ArrayList<Reservation> R_list = new ArrayList<Reservation>();
        while(c.moveToNext()){
            num=c.getString(0);
            date=c.getString(1);
            R_list.add(new Reservation(num,date));


        }ArrayList<HashMap<String, String>>mapList = new ArrayList<HashMap<String,String>>();
        for(Reservation r : R_list){

            HashMap  map  = new HashMap();
            System.out.println(r.r_num+","+r.r_date);

            map.put("num", r.r_num);

            map.put("date", r.r_date);

            mapList.add(map);

        }





        listView = (ListView)findViewById(R.id.list);



        SimpleAdapter sap = new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, new String[]{"num", "date"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(sap);




    }
    class Reservation{

        String r_num;

        String r_date;

        public Reservation(String r_num, String r_date){

            this.r_num = r_num;

            this.r_date = r_date;

        }

    }
}
