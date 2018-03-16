package com.example.user_pc.ezbooking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by SOPHIE on 2016. 8. 25..
 */
public class customer_reservation_detail extends AppCompatActivity{
    private TextView ReserDetail, ReserNum, ReserDate, ReserTime, ReserName, ReserMemo,ReserMany,ReserApproval;
    private TextView detail_ReserNum,detail_ReserDate, detail_ReserTime, detail_ReserName, detail_ReserMemo,detail_ReserMany,detail_ReserApproval;
    private Button b_Edit,b_Cancel,b_Ok;
    private String num,id,r_id,date, time, persons_num, no_show,memo, approval,rName;
    private int canNotCancel = 0;
    private String today;
    public static AppCompatActivity customerReserDetailActivity;
    public static boolean isRunning = false;

    SQLiteDatabase db;
    DBhelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_customer_reservation_detail);
        isRunning = true;
        customerReserDetailActivity = customer_reservation_detail.this;

        reservation_choose chooseActivity = (reservation_choose)reservation_choose.reservationChooseActivity;
        chooseActivity.finish();

        helper = new DBhelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }
        Log.d("DD", "SELECT");

        id = getIntent().getExtras().getString("id");
        num = getIntent().getExtras().getString("reservationNum");

        ReserDetail = (TextView) findViewById(R.id.ReserDetail);
        ReserNum = (TextView) findViewById(R.id.ReserNum);
        ReserDate = (TextView) findViewById(R.id.ReserDate);
        ReserTime = (TextView) findViewById(R.id.ReserTime);
        ReserName = (TextView) findViewById(R.id.ReserName);
        ReserMemo = (TextView) findViewById(R.id.ReserMemo);
        ReserMany = (TextView) findViewById(R.id.ReserMany);
        ReserApproval = (TextView) findViewById(R.id.ReserApproval);

        Cursor c = db.rawQuery("SELECT r_id, date, time, persons_num,memo, approval FROM reservation where num= ? ", new String[]{num});

        r_id = "";
        date = "";
        time = "";
        persons_num = "";
        memo = "";
        approval = "";

        while(c.moveToNext()){
            r_id=c.getString(0);
            date=c.getString(1);
            time=c.getString(2);
            persons_num=c.getString(3);
            memo=c.getString(4);
            approval=c.getString(5);
        }

        c = db.rawQuery("SELECT name FROM restaurant where r_id = ?",new String[]{r_id});
        rName = "";
        while(c.moveToNext()){
            rName=c.getString(0);
        }

        detail_ReserNum = (TextView) findViewById(R.id.detail_ReserNum);
        detail_ReserNum.setText(num);

        detail_ReserDate = (TextView) findViewById(R.id.detail_ReserDate);
        detail_ReserDate.setText(date);

        detail_ReserTime = (TextView) findViewById(R.id.detail_ReserTime);
        detail_ReserTime.setText(time);

        detail_ReserName = (TextView) findViewById(R.id.detail_ReserName);
        detail_ReserName.setText(rName);

        detail_ReserMemo = (TextView) findViewById(R.id.detail_ReserMemo);
        detail_ReserMemo.setText(memo);

        detail_ReserMany = (TextView) findViewById(R.id.detail_ReserMany);
        detail_ReserMany.setText(persons_num);

        detail_ReserApproval = (TextView) findViewById(R.id.detail_ReserApproval);
        detail_ReserApproval.setText(approval);

        b_Edit = (Button) findViewById(R.id.b_Edit);
        b_Cancel = (Button) findViewById(R.id.b_Cancel);
        b_Ok = (Button) findViewById(R.id.b_Ok);

        b_Edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//확인다이얼로그 띄우고 현재 예약데이터 삭제후 예약화면으로 넘어가기
                checkEditDialog();
            }
        });

        b_Cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//확인다이얼로그 띄우고 현재 예약데이터 삭제하기
                isToday();
                if (canNotCancel == 0)
                    checkCancelDialog();
                else
                    todayCancelDialog();
            }
        });

        b_Ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//현재 액티비티 종료
                finish();
            }
        });
    }

    private void checkEditDialog(){//예약내용 확인받기
        AlertDialog.Builder EditConfirm = new AlertDialog.Builder(customer_reservation_detail.this);
        EditConfirm.setTitle("예약수정");
        String checkMsg = "예약수정은 현재 예약건의 삭제후에 수정된 예약의 재요청이 진행됩니다.\n따라서 예약하신 날짜의 전일,혹은 당일에 수정을 하신다면 요청하신 시간까지 승인여부 확인을 보장할수 없습니다.\n진행할까요?";
        EditConfirm.setMessage(checkMsg).setCancelable(false).setPositiveButton("확인",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'YES'
                db.execSQL("DELETE FROM reservation WHERE num = ?",new String[]{num});
                goIntent();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'No'
                return;
            }
        });
        AlertDialog alert = EditConfirm.create();
        alert.show();
    }

    private void checkCancelDialog(){//
        AlertDialog.Builder CancelConfirm = new AlertDialog.Builder(customer_reservation_detail.this);
        CancelConfirm.setTitle("예약취소");
        String checkMsg = "현재 예약건을 취소할까요?";
        CancelConfirm.setMessage(checkMsg).setCancelable(false).setPositiveButton("확인",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'YES'
                db.execSQL("DELETE FROM reservation WHERE num = ?",new String[]{num});
                finish();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'No'
                return;
            }
        });
        AlertDialog alert = CancelConfirm.create();
        alert.show();
    }

    private void todayCancelDialog(){
        AlertDialog.Builder todayCancelConfirm = new AlertDialog.Builder(customer_reservation_detail.this);
        todayCancelConfirm.setTitle("예약취소");
        String checkMsg = "당일취소는 고객님의 FAME에 영향을 미칠 수 있습니다.\n진행하시겠습니까?";
        todayCancelConfirm.setMessage(checkMsg).setCancelable(false).setPositiveButton("확인",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'YES'
                //디비에 노쇼카운트 업데이트
                Cursor c = db.rawQuery("SELECT fame FROM customer where id = ?",new String[]{id});
                int fame = 0 ;
                while(c.moveToNext()){
                    fame=c.getInt(0);
                }
                fame++ ;
                db.execSQL("UPDATE customer SET fame = ? WHERE id =?", new Object[]{fame,id});
                db.execSQL("DELETE FROM reservation WHERE num = ?",new String[]{num});
                finish();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'No'
                return;
            }
        });
        AlertDialog alert = todayCancelConfirm.create();
        alert.show();
    }

    private void isToday(){//당일취소비교
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        today = mYear + isSmallerTen(mMonth+1) + isSmallerTen(mDay);
        if (today.equals(date))
            canNotCancel = 1;
        else
            canNotCancel = 0;
    }

    private String isSmallerTen(int num){
        String reNum;
        if (num<10)
            reNum = "0"+num;
        else
            reNum = ""+num;
        return reNum;
    }

    private void goIntent(){
        Intent intent = new Intent(customer_reservation_detail.this, reservation_choose.class);
        Bundle data = new Bundle();
        data.putString("r_id",r_id);
        data.putString("id",id);
        intent.putExtras(data);
        startActivity(intent);
    }
}
