package com.example.user_pc.ezbooking;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user-pc on 2016-09-17.
 */
public class CouponCheck extends AppCompatActivity {
    private List<String> coupons = new ArrayList<String>();
    private ArrayAdapter<String> mAdapter;
    private ListView lv;
    private Button set;
    private TextView tv_currentset;
    private String checking = "", code, currentset, Id;
    SQLiteDatabase db;
    DBhelper helper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponcheck);

        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        Id = getIntent().getExtras().getString("r_id");

        coupons.add("음료 1잔 제공 - 500p");
        coupons.add("음료 2잔 제공 - 1000p");
        coupons.add("5% 할인 - 1000p");
        coupons.add("10% 할인 - 1500p");

        tv_currentset = (TextView) findViewById(R.id.currentset);
        Cursor c = db.rawQuery("SELECT coupon FROM restaurant where r_id= ? ", new String[]{Id});
        while (c.moveToNext()) {
            code = c.getString(0);
        }
        currentset = decoding(code);
        tv_currentset.setText("*** 현재 제공 쿠폰 ***\n\n"+currentset);


        mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                coupons
        );

        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(mAdapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        set = (Button) findViewById(R.id.setting);


        set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SparseBooleanArray checked = lv.getCheckedItemPositions();
                ArrayList<Integer> a = new ArrayList<Integer>();
                String c1="0",c2="0",c3="0",c4="0";
                for (int i=0; i<checked.size(); i++) {
                    if (checked.valueAt(i))
                        a.add(checked.keyAt(i));
                }

                for(int j=0; j<a.size(); j++){
                    if(a.get(j)==0)
                        c1="1";
                    else if(a.get(j)==1)
                        c2="1";
                    else if(a.get(j)==2)
                        c3="1";
                    else if(a.get(j)==3)
                        c4="1";
                }

                checking = c1+c2+c3+c4;


                lv.clearChoices();
                Toast.makeText(getApplicationContext(), checking, Toast.LENGTH_LONG).show();
                db.execSQL("UPDATE restaurant SET coupon = ? WHERE r_id =?", new String[]{checking, Id});

                Intent intent = new Intent(CouponCheck.this, restaurant_page.class);
                Bundle data = new Bundle();
                data.putString("r_id", Id);
                intent.putExtras(data);
                startActivity(intent);
                finish();

            }
        });

    }

    public String decoding(String code){
        String result ="";
        String c1,c2,c3,c4;
        c1 = code.substring(0,1);
        c2 = code.substring(1,2);
        c3 = code.substring(2,3);
        c4 = code.substring(3,4);
        String C1,C2,C3,C4;
        C1 = "음료 1잔 제공 쿠폰";
        C2 = "음료 2잔 제공 쿠폰";
        C3 = "5% 할인 쿠폰";
        C4 = "10% 할인 쿠폰";
        if(c1.equals("0") && c2.equals("0") && c3.equals("0") && c3.equals("0"))
            result = "현재 제공하는 쿠폰이 없습니다\n";
        if (c1.equals("1"))
            result = result+C1+"\n";
        if (c2.equals("1"))
            result = result+C2+"\n";
        if (c3.equals("1"))
            result = result+C3+"\n";
        if (c4.equals("1"))
            result = result+C4+"\n";
        return result;
    }

}
