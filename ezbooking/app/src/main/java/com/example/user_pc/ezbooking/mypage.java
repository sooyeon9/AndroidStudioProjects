package com.example.user_pc.ezbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


/**
 * Created by SOPHIE on 2016. 7. 31..
 */
public class mypage extends AppCompatActivity {
    private String Id;
    private TextView My_Name, My_Point, My_Point_Now;
    private Button b_My_Reservation, b_My_Coupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        //Id = getIntent().getExtras().getString("id");

        My_Name = (TextView) findViewById(R.id.my_name);
        My_Point = (TextView) findViewById(R.id.my_point);
        My_Point_Now = (TextView) findViewById(R.id.my_point_now);

        b_My_Coupon = (Button) findViewById(R.id.my_coupon);
        b_My_Reservation = (Button) findViewById(R.id.my_reservation);

        //My_Name.setText("");
        //My_Point_Now.setText("");


        b_My_Coupon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
            }
        });

        b_My_Reservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
            }
        });

    }


}
