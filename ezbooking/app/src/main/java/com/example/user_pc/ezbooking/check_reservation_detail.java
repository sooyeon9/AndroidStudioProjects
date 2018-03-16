package com.example.user_pc.ezbooking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by SOPHIE on 2016. 8. 12..
 */
public class check_reservation_detail extends AppCompatActivity {

    private TextView detail_ReserDate, detail_ReserTime, detail_ReserNum, detail_ReserName, detail_ReserPhoneNum, detail_ReserMany, detail_ReserMemo, detail_ReserNoShow, detail_ReserApproval;
    private Button b_ok, b_NoShow,b_end;
    SQLiteDatabase db;
    DBhelper helper;
    private String reservation_num,customer_id,r_id;
    private int mYear, mMonth,mDay,mTotal,mDate,no_show;
    private String STotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_reservation_detail);
        reservation_num = getIntent().getExtras().getString("input_reservation");
        r_id = getIntent().getExtras().getString("r_id");
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DATE);

        String sYear = Integer.toString(mYear);
        String sMonth="";
        if(mMonth>=10) {
            sMonth = Integer.toString(mMonth);
        }if(mMonth<10){
            sMonth = "0"+Integer.toString(mMonth);
        }
        String sDay = "";
        if(mDay>=10){
            sDay=Integer.toString(mDay);
        }if(mDay<10){
            sDay="0"+Integer.toString(mDay);
        }
        STotal = sYear+sMonth+sDay;
        mTotal = Integer.parseInt(STotal);
        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        detail_ReserDate = (TextView) findViewById(R.id.detail_ReserDate);
        detail_ReserTime = (TextView) findViewById(R.id.detail_ReserTime);
        detail_ReserNum = (TextView) findViewById(R.id.detail_ReserNum);
        detail_ReserName = (TextView) findViewById(R.id.detail_ReserName);
        detail_ReserPhoneNum = (TextView) findViewById(R.id.detail_ReserPhoneNum);
        detail_ReserMany = (TextView) findViewById(R.id.detail_ReserMany);
        detail_ReserMemo = (TextView) findViewById(R.id.detail_ReserMemo);
        detail_ReserNoShow = (TextView) findViewById(R.id.detail_ReserNoShow);
        detail_ReserApproval = (TextView) findViewById(R.id.detail_ReserApproval);

        Log.d("DD", "SELECT");

        Cursor cursor = db.rawQuery("SELECT date,time,customer_id,persons_num,no_show,memo,approval FROM reservation where num= ? ", new String[]{reservation_num});
        String date = "";
        String time = "";
        Integer per_num=0;
        String noshow = "";
        String menu="";
        String approval="";

        while (cursor.moveToNext()) {
            date = cursor.getString(0);
            time = cursor.getString(1);
            customer_id=cursor.getString(2);
            per_num=cursor.getInt(3);
            noshow=cursor.getString(4);
            menu=cursor.getString(5);
            approval=cursor.getString(6);

        }
        String s_per_num = Integer.toString(per_num);

        Log.d("DD", "SELECT");

        Cursor d = db.rawQuery("SELECT phone FROM customer where id= ? ", new String[]{customer_id});
        String phone="";

        while(d.moveToNext()){
            phone=d.getString(0);
        }




        detail_ReserDate.setText(date);
        detail_ReserTime.setText(time);
        detail_ReserNum.setText(reservation_num);
        detail_ReserName.setText(customer_id);
        detail_ReserPhoneNum.setText(phone);
        detail_ReserMany.setText(s_per_num);
        detail_ReserMemo.setText(menu);
        detail_ReserNoShow.setText(noshow);
        detail_ReserApproval.setText(approval);
        mDate = Integer.parseInt(date);
        b_ok = (Button) findViewById(R.id.b_ok);
        b_NoShow = (Button) findViewById(R.id.b_NoShow);
        b_end=(Button)findViewById(R.id.b_end);

        b_end.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(check_reservation_detail.this, restaurant_all_reservation.class);
                intent.putExtra("r_id",r_id);
                startActivity(intent);
                finish();
            }
        });
        b_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String sql = "update reservation set approval = 'yes' where num = '"+reservation_num +"';";
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "예약 승인 완료되었습니다.", Toast.LENGTH_SHORT).show();
                detail_ReserApproval.setText("yes");

            }
        });

        b_NoShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mDate > mTotal) {
                    Toast.makeText(getApplicationContext(), "예약날짜("+mDate+") 이후부터 No Show 체크 가능!", Toast.LENGTH_SHORT).show();
                }else{
                    checkOkDialog();

                }

            }
        });

    }
    private void NoShowCheck(){
        Log.d("DD", "SELECT");

        Cursor d = db.rawQuery("SELECT noshow FROM customer where id= ? ", new String[]{customer_id});
        while(d.moveToNext()){
            no_show=d.getInt(0);
        }
        Integer newNoshow = no_show+1;

        String sql = "update customer set noshow = " + newNoshow +" where id = '"+customer_id +"';";
        db.execSQL(sql);
        String sql3 = "update reservation set no_show = 'yes' where num = '"+reservation_num +"';";
        db.execSQL(sql3);

        if(newNoshow>=2){
            String sql2 = "update customer set fame = " + 2 +" where id = '"+customer_id +"';";
            db.execSQL(sql2);
        }else if(newNoshow>=4){
            String sql2 = "update customer set fame = " + 3 +" where id = '"+customer_id +"';";
            db.execSQL(sql2);
        }else if(newNoshow>=7){
            String sql2 = "update customer set fame = " + 4 +" where id = '"+customer_id +"';";
            db.execSQL(sql2);
        }else if(newNoshow>=10){
            String sql2 = "update customer set fame = " + 5 +" where id = '"+customer_id +"';";
            db.execSQL(sql2);
        }
        detail_ReserNoShow.setText("yes");

    }
    private void checkOkDialog(){//예약내용 확인받기
        AlertDialog.Builder reservationConfirm = new AlertDialog.Builder(check_reservation_detail.this);
        reservationConfirm.setTitle("NoShow 체크 여부 확인");
        final String checkMsg = "NoShow 체크 시 고객의 fame 등급이 내려갑니다. 계속하시겠습니까?";
        reservationConfirm.setMessage(checkMsg).setCancelable(false).setPositiveButton("확인",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'YES'
                NoShowCheck();
                Toast.makeText(check_reservation_detail.this,"NoShow가 체크되었습니다.",Toast.LENGTH_SHORT).show();

            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'No'
                Toast.makeText(check_reservation_detail.this,"취소되었습니다.",Toast.LENGTH_SHORT).show();

                return;
            }
        });
        AlertDialog alert = reservationConfirm.create();
        alert.show();
    }
}

