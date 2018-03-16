package com.example.user_pc.ezbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class restaurant_page extends AppCompatActivity {
    private String Id, Rest_Name;
    private TextView tv_Rest_Name;
    private Button b_Rest_Reservation, b_Rest_Ed_info, b_Rest_Coup_Set;
    SQLiteDatabase db;
    DBhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_page);
        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        Id = getIntent().getExtras().getString("r_id");

        b_Rest_Reservation = (Button) findViewById(R.id.restaurant_reservation);
        b_Rest_Ed_info = (Button) findViewById(R.id.restaurant_edit_info);
        b_Rest_Coup_Set = (Button) findViewById(R.id.restaurant_coupon_setting);
        tv_Rest_Name = (TextView) findViewById(R.id.restName);

        Cursor c = db.rawQuery("SELECT name FROM restaurant where r_id= ? ", new String[]{Id});
        while (c.moveToNext()) {
            Rest_Name = c.getString(0);
        }
        tv_Rest_Name.setText(Rest_Name+"님");


        b_Rest_Reservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(restaurant_page.this, restaurant_all_reservation.class);
                Bundle data = new Bundle();
                data.putString("r_id",Id);
                intent.putExtras(data);
                startActivity(intent);
            }
        });

        b_Rest_Ed_info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(restaurant_page.this, restaurant_edit_info.class);
                Bundle data = new Bundle();
                data.putString("r_id",Id);
                intent.putExtras(data);
                startActivity(intent);
            }
        });

        b_Rest_Coup_Set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(restaurant_page.this, CouponCheck.class);
                Bundle data = new Bundle();
                data.putString("r_id",Id);
                intent.putExtras(data);
                startActivity(intent);
            }
        });

    }
}

