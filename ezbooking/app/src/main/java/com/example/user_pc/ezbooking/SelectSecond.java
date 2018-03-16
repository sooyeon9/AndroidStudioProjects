package com.example.user_pc.ezbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

public class SelectSecond extends AppCompatActivity {

    SQLiteDatabase db;
    DBhelper helper;

    private TextView tv_pickedRId,tv_rAddress,tv_rNum,tv_rOpClHour,tv_rHoliday,tv_rInfo;
    private Button b_goReservation;
    private ImageView iv_mainImage;
    private SelectSecond_ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private String id,r_id,RName,RAddress,RPhone,ROpClHour,RHoliday,RInfo; // 인텐트로 받아올 데이터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_second);

        helper = new DBhelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }
        Log.d("DD", "SELECT");

        id = getIntent().getExtras().getString("id");
        r_id = getIntent().getExtras().getString("selected_r_id");

        Cursor c = db.rawQuery("SELECT name,phone,info,Do_Si_address,Gu_address,other_address,holiday,open,close FROM restaurant where r_id= ? ", new String[]{r_id});
        RName = "";
        RPhone = "";
        RInfo = "";
        RAddress = "";
        RHoliday = "";
        ROpClHour = "";

        while(c.moveToNext()){
            RName=c.getString(0);
            RPhone=c.getString(1);
            RInfo=c.getString(2);
            RAddress=c.getString(4)+" "+c.getString(5);
            RHoliday=c.getString(6);
            ROpClHour=c.getString(7)+" - "+c.getString(8);
        }
        RPhone = RPhone.substring(0,3)+"-"+RPhone.substring(3,7)+"-"+RPhone.substring(7);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        View header = getLayoutInflater().inflate(R.layout.layout_select_second_list_header,null,false);
        expListView.addHeaderView(header);
        View footer = getLayoutInflater().inflate(R.layout.layout_select_second_list_footer,null,false);
        expListView.addFooterView(footer);

        iv_mainImage = (ImageView) header.findViewById(R.id.mainImage);
        String resName = "@drawable/"+r_id;
        String packName = this.getPackageName();
        int resID = this.getResources().getIdentifier(resName,"drawable",packName);
        iv_mainImage.setImageResource(resID);

        tv_pickedRId = (TextView) header.findViewById(R.id.pickedRId);
        tv_pickedRId.setText(RName);

        tv_rInfo = (TextView) header.findViewById(R.id.rInfo);
        tv_rInfo.setText("가게소개   "+RInfo);

        tv_rAddress = (TextView) header.findViewById(R.id.rAddress);
        tv_rAddress.setText("가게주소   "+RAddress);

        tv_rNum = (TextView) header.findViewById(R.id.rPhone);
        tv_rNum.setText("전화번호   "+RPhone);

        tv_rOpClHour = (TextView) header.findViewById(R.id.rOpClHour);
        tv_rOpClHour.setText("영업시간   "+ROpClHour);

        tv_rHoliday = (TextView) header.findViewById(R.id.rHoliday);
        tv_rHoliday.setText("휴무정보   "+RHoliday);

        iv_mainImage = (ImageView) header.findViewById(R.id.mainImage);

        b_goReservation = (Button) footer.findViewById(R.id.b_goReservation);
        b_goReservation.setText("예약하기");

        b_goReservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkOkDialog();
            }
        });
        // preparing list data
        prepareListData();

        listAdapter = new SelectSecond_ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });
    }
    //메뉴리스트 준비
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("메뉴");

        // Adding child data
        List<String> menu = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT menu_name,price FROM menu where r_id= ? ", new String[]{r_id});
        String menu_name = "";
        String price ;
        while(c.moveToNext()){
            menu_name=c.getString(0);
            price=c.getString(1);
            menu.add(menu_name+"  /  "+price);
        }
        listDataChild.put(listDataHeader.get(0), menu); // Header, Child data
    }

    private void checkOkDialog(){//예약내용 확인받기
        AlertDialog.Builder FameConfirm = new AlertDialog.Builder(SelectSecond.this);
        FameConfirm.setTitle("안내");
        String checkMsg = "고객님의 FAME등급이 가게의 승인결정에 영향을 미칠 수 있으니 유의해주시기 바랍니다.";
        FameConfirm.setMessage(checkMsg).setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'YES'
                Intent intent = new Intent(SelectSecond.this, reservation_choose.class);
                Bundle data = new Bundle();
                data.putString("r_id",r_id);
                data.putString("id",id);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
        AlertDialog alert = FameConfirm.create();
        alert.show();
    }
}
