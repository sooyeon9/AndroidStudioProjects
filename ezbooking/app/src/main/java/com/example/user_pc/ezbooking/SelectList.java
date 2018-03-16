package com.example.user_pc.ezbooking;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SelectList extends ListActivity {
    private String Id, sido, gungu, food;
    private int price;

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> address = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();

    private void checkOkDialog(){//예약내용 확인받기
        AlertDialog.Builder FameConfirm = new AlertDialog.Builder(SelectList.this);
        FameConfirm.setTitle("안내");
        String checkMsg = "검색 결과가 존재하지 않습니다!";
        FameConfirm.setMessage(checkMsg ).setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'YES'
                finish();
            }
        });
        AlertDialog alert = FameConfirm.create();
        alert.show();
    }

    SQLiteDatabase db;
    DBhelper helper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_selectlist);

        helper = new DBhelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }
        Log.d("DD", "SELECT");


        Id = getIntent().getExtras().getString("id");

        sido = getIntent().getExtras().getString("sido");
        gungu = getIntent().getExtras().getString("gungu");
        price = getIntent().getExtras().getInt("price");
        food = getIntent().getExtras().getString("food");


        if(price==0){
            if(food.equals("선택안함"))
                case1();
            else
                case3();
        } else {
            if(food.equals("선택안함"))
                case2();
            else
                case4();
        }

        if(images.size()==0)
            checkOkDialog();


        setListAdapter(new CustomAdapter(this, name, address, prices, images));
    }




    protected void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        final int number=position;

        Intent intent = new Intent(SelectList.this, SelectSecond.class);
        Bundle data = new Bundle();
        data.putString("id",Id);
        data.putString("selected_r_id", images.get(position));
        intent.putExtras(data);
        startActivity(intent); // 다음 화면으로 넘어간다

    }


    //case1 : 가격대(x) 음식분류(x) - [지역]
    public void case1(){
        Cursor c = db.rawQuery("SELECT r_id,name,other_address,category FROM restaurant where Do_Si_address= ? and Gu_address= ?", new String[]{sido,gungu});
        while (c.moveToNext()) {
            images.add(c.getString(0));
            name.add(c.getString(1));
            address.add(sido+" "+gungu+" "+c.getString(2));
            prices.add(c.getString(3)+" - "+getPrices(c.getString(0))+"원대");
        }
    }


    //case2 : 가격대(o) 음식분류(x) - [지역] & [가격대]
    public void case2(){
        Cursor c = db.rawQuery("SELECT r_id,name,other_address,category FROM restaurant where Do_Si_address= ? and Gu_address= ?", new String[]{sido,gungu});
        while (c.moveToNext()) {
            int thisPrices = getPricesInt(c.getString(0));
            if(price==10000){
                if(thisPrices<=10000){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(c.getString(3)+" - "+thisPrices+"원대");
                }
            } else if(price==20000){
                if(10000<thisPrices && thisPrices<=20000){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(c.getString(3)+" - "+thisPrices+"원대");
                }
            } else if(price==30000){
                if(20000<thisPrices && thisPrices<=30000){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(c.getString(3)+" - "+thisPrices+"원대");
                }
            } else {
                if(30000<thisPrices){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(c.getString(3)+" - "+thisPrices+"원대");
                }
            }
        }
    }


    //case3 : 가격대(x) 음식분류(o) - [지역] & [음식분류]
    public void case3(){
        Cursor c = db.rawQuery("SELECT r_id,name,other_address FROM restaurant where Do_Si_address= ? and Gu_address= ? and category= ?", new String[]{sido,gungu,food});
        while (c.moveToNext()) {
            images.add(c.getString(0));
            name.add(c.getString(1));
            address.add(sido+" "+gungu+" "+c.getString(2));
            prices.add(food+" - "+getPrices(c.getString(0))+"원대");
        }
    }


    //case4 : 가격대(o) 음식분류(o) - [지역] & [가격대] & [음식분류]
    public void case4(){
        Cursor c = db.rawQuery("SELECT r_id,name,other_address FROM restaurant where Do_Si_address= ? and Gu_address= ? and category= ?", new String[]{sido,gungu,food});
        while (c.moveToNext()) {
            int thisPrices = getPricesInt(c.getString(0));
            if(price==10000){
                if(thisPrices<=10000){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(food+" - "+thisPrices+"원대");
                }
            } else if(price==20000){
                if(10000<thisPrices && thisPrices<=20000){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(food+" - "+thisPrices+"원대");
                }
            } else if(price==30000){
                if(20000<thisPrices && thisPrices<=30000){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(food+" - "+thisPrices+"원대");
                }
            } else {
                if(30000<thisPrices){
                    images.add(c.getString(0));
                    name.add(c.getString(1));
                    address.add(sido+" "+gungu+" "+c.getString(2));
                    prices.add(food+" - "+thisPrices+"원대");
                }
            }
        }
    }




    public String getPrices(String r_id){
        int totalPrice=0, num=0;
        String result;
        Cursor c = db.rawQuery("SELECT price FROM menu where r_id= ? ", new String[]{r_id});
        while (c.moveToNext()) {
            totalPrice += c.getInt(0);
            num++;
        }
        result = String.valueOf(totalPrice/num);
        return result;
    }


    public int getPricesInt(String r_id){
        int totalPrice=0, num=0;
        Cursor c = db.rawQuery("SELECT price FROM menu where r_id= ? ", new String[]{r_id});
        while (c.moveToNext()) {
            totalPrice += c.getInt(0);
            num++;
        }
        return totalPrice/num;
    }



}

