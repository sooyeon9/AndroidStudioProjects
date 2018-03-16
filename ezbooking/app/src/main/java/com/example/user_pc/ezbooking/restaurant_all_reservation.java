package com.example.user_pc.ezbooking;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class restaurant_all_reservation extends ListActivity {


    private String r_id;
    private ArrayList reservation = new ArrayList();
    SQLiteDatabase db;
    DBhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // simple adapter test
        ArrayList<HashMap<String, String>> mlist = new ArrayList<HashMap<String, String>> ();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_all_reservation);


        r_id = getIntent().getExtras().getString("r_id");

        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }


        Log.d("DD", "SELECT");

        Cursor c = db.rawQuery("SELECT num,date,approval FROM reservation where r_id= ? ", new String[]{r_id});
        String date = "";
        String num = "";
        String approval = "";
        ArrayList<Reservation> R_list = new ArrayList<Reservation>();
        while(c.moveToNext()){
            num=c.getString(0);
            date=c.getString(1);
            approval = c.getString(2);
            reservation.add(num);
            if(approval.equals("yes")){
                num="[승인] "+num;
            }else if(approval.equals("no")){
                num="[비승인] "+num;
            }
            R_list.add(new Reservation(num,date));


        }ArrayList<HashMap<String, String>>mapList = new ArrayList<HashMap<String,String>>();
        for(Reservation r : R_list){

            HashMap  map  = new HashMap();

            map.put("num", r.r_num);

            map.put("date", r.r_date);

            mapList.add(map);

        }








        SimpleAdapter sap = new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, new String[]{"num", "date"}, new int[]{android.R.id.text1, android.R.id.text2});
        setListAdapter(sap);




    }
    class Reservation{

        String r_num;

        String r_date;

        public Reservation(String r_num, String r_date){

            this.r_num = r_num;

            this.r_date = r_date;

        }

    }

    protected void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        final int number=position;


                String reservation_num= (String) reservation.get(number);
                Intent intent = new Intent(
                        restaurant_all_reservation.this, // 현재 화면의 제어권자
                        check_reservation_detail.class);// 다음 넘어갈 클래스 지정
                intent.putExtra("input_reservation", reservation_num);//intent에 담아서 넘긴다
                intent.putExtra("r_id",r_id);
                startActivity(intent); // 다음 화면으로 넘어간다
        finish();
            }




}
