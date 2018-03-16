package com.example.user_pc.ezbooking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

public class SelectFirst extends Activity implements AdapterView.OnItemSelectedListener{

    private HashMap<Integer,String> hashMap = new HashMap<>();

    private Spinner sp_sido, sp_gungu, sp_food;
    private String Id,sido, gungu, food;
    private RadioGroup rg_price;
    private int price=0;
    private Button b_search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.layout_selectfirst);


        //Login loginActivity = (Login)Login.loginActivity;
        //loginActivity.finish();

        //Id = getIntent().getExtras().getString("id"); //현재 접속자 아이디

        rg_price = (RadioGroup)findViewById(R.id.pricegroup);

        sp_sido = (Spinner)findViewById(R.id.sidoSpinner);
        sp_gungu = (Spinner)findViewById(R.id.gunguSpinner);
        sp_food = (Spinner)findViewById(R.id.foodSpinner);

        b_search = (Button)findViewById(R.id.search);


        hashMap.put(0,"seoul_sigungu_category");
        hashMap.put(1,"geonggi_sigungu_category");
        hashMap.put(2,"gangone_sigungu_category");


        sp_sido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                sido = parent.getItemAtPosition(position).toString();
                addItemOnSp_gungu(hashMap.get(position));
                Toast.makeText(getApplicationContext(), sido, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sido = "서울특별시";
                addItemOnSp_gungu(hashMap.get(0));
            }
        });



        /*

        sp_gungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                gungu = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), gungu, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gungu = "강남구";
            }
        });

        */




        sp_food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                food = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), food, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                food = "선택안함";
            }
        });




        rg_price.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.nope:
                        price = 0;
                        break;
                    case R.id.under10000:
                        Toast.makeText(getApplicationContext(), "10,000원 이하", Toast.LENGTH_LONG).show();
                        price = 10000;
                        break;
                    case R.id.under20000:
                        Toast.makeText(getApplicationContext(), "10,000~20,000원", Toast.LENGTH_LONG).show();
                        price = 20000;
                        break;
                    case R.id.under30000:
                        Toast.makeText(getApplicationContext(), "20,000~30,000원", Toast.LENGTH_LONG).show();
                        price = 30000;
                        break;
                    case R.id.over30000:
                        Toast.makeText(getApplicationContext(), "30,000원 이상", Toast.LENGTH_LONG).show();
                        price = 30001;
                        break;

                }
            }
        });


        b_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SelectFirst.this, SelectList.class);
                Bundle data = new Bundle();
                data.putString("id",Id);
                data.putString("sido",sido);
                data.putString("gungu",gungu);
                data.putInt("price", price);
                data.putString("food", food);
                intent.putExtras(data);
                startActivity(intent);
            }
        });





    }



    public void addItemOnSp_gungu(String sido_name){
        List<String> list = new ArrayList<String>();

        String resName = "@array/"+sido_name;
        String packName = getPackageName();
        int resID = getResources().getIdentifier(resName,"array",packName);

        for(int i=0; i<this.getResources().getStringArray(resID).length; i++)
            list.add(this.getResources().getStringArray(resID)[i]);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gungu.setAdapter(dataAdapter);

        sp_gungu.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position,long arg3) {
        int id = parent.getId();
        switch (id)
        {
            case R.id.gunguSpinner:
                gungu = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), gungu, Toast.LENGTH_LONG).show();
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView parent) {
        // Do nothing
        gungu = "강남구";
    }



    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action :
                Intent intent = new Intent(SelectFirst.this, mypage.class);
                Bundle data = new Bundle();
                data.putString("id",Id);
                intent.putExtras(data);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
