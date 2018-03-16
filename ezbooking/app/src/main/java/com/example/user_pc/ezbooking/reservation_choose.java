package com.example.user_pc.ezbooking;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.content.Intent;
/**
 * Created by SOPHIE on 2016. 8. 25..
 */
public class reservation_choose extends AppCompatActivity{

    SQLiteDatabase db;
    DBhelper helper;

    public static AppCompatActivity reservationChooseActivity;
    private String id,r_id,reservationNum;

    private Button b_approval_please,b_choosing_date, b_choosing_time, b_choosing_persons;
    private TextView tv_reservation, tv_pickedRId, tv_pickedDate, tv_pickedTime, tv_pickedPersonsNum,tv_memo;
    private EditText et_memo;
    private int mYear, mMonth, mDay, mHour, mMinute,mPersonNum;
    private String mAmPm = "",mMemo,rDate,rTime,rName;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reservation_choose);
        reservationChooseActivity = reservation_choose.this;

        if(customer_reservation_detail.isRunning){
            customer_reservation_detail detailActivity = (customer_reservation_detail)customer_reservation_detail.customerReserDetailActivity;
            detailActivity.finish();
        }

        helper = new DBhelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }
        Log.d("DD", "SELECT");

        id = getIntent().getExtras().getString("id");
        r_id = getIntent().getExtras().getString("r_id");

        Cursor cursor = db.rawQuery("SELECT name FROM restaurant where r_id= ? ", new String[]{r_id});
        rName = "";
        while(cursor.moveToNext()) {
            rName = cursor.getString(0);
        }

        tv_reservation = (TextView) findViewById(R.id.reservation);
        tv_reservation.setText("예약하기");
        tv_memo = (TextView) findViewById(R.id.memo);
        tv_memo.setText("MEMO");

        et_memo = (EditText) findViewById(R.id.et_memo);

        tv_pickedRId = (TextView) findViewById(R.id.picked_r_id);
        tv_pickedRId.setText(rName);

        tv_pickedDate = (TextView) findViewById(R.id.date);
        tv_pickedTime = (TextView) findViewById(R.id.time);
        tv_pickedPersonsNum = (TextView) findViewById(R.id.persons_num);
        tv_pickedPersonsNum.setText("0");

        b_choosing_date = (Button) findViewById(R.id.b_choosing_date);
        b_choosing_time = (Button) findViewById(R.id.b_choosing_time);
        b_choosing_persons = (Button) findViewById(R.id.b_choosing_persons);
        b_approval_please = (Button) findViewById(R.id.b_approval_please);

        b_choosing_date.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {//날짜선택창 띄우기
                showDialog(DATE_DIALOG_ID);
            }
        });

        b_choosing_time.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {//시간 선택창 띄우기
                showDialog(TIME_DIALOG_ID);
            }
        });

        b_choosing_persons.setOnClickListener(new OnClickListener() {//숫자선택창 띄우기
            public void onClick(View v) {
                personsNumPickDialog();
            }
        });


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        updateData();

        b_approval_please.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                updateData();
                checkOkDialog();
            }
        });
    }

        private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {//날짜선택창
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        updateData();
                        String dateMsg = mYear + "년 " + isSmallerTen(mMonth+1) + "월 " + isSmallerTen(mDay) + "일 선택하셨습니다.";
                        Toast.makeText(reservation_choose.this, dateMsg, Toast.LENGTH_SHORT).show();
                    }
                };
        private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {//시간선택창

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                    updateData();
                    if (mHour<12)
                        mAmPm = "오전 ";
                    else
                        mAmPm = "오후 ";
                    String timeMsg = mAmPm+mHour+"시 "+isSmallerTen(mMinute)+"분 선택하셨습니다.";
                    Toast.makeText(reservation_choose.this,timeMsg,Toast.LENGTH_SHORT).show();
                }
            };
    private void personsNumPickDialog(){//인원수 선택창
        NumberPicker mPersonNumPicker = new NumberPicker(this);
        mPersonNumPicker.setMaxValue(50);
        mPersonNumPicker.setMinValue(1);
        NumberPicker.OnValueChangeListener mValChangedListener = new NumberPicker.OnValueChangeListener(){

            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                mPersonNum = newVal;
                updateData();
            }
        };
        mPersonNumPicker.setOnValueChangedListener(mValChangedListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(mPersonNumPicker);
        builder.setTitle("인원 선택");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(reservation_choose.this, "" + mPersonNum + "명 선택하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void checkOkDialog(){//예약내용 확인받기
        AlertDialog.Builder reservationConfirm = new AlertDialog.Builder(reservation_choose.this);
        reservationConfirm.setTitle("예약확인");
        String checkMsg = "날짜 : "+mYear+"년 "+ isSmallerTen(mMonth+1)+"월 "+ isSmallerTen(mDay)+"일\n"+"시간 : "+mAmPm+" "+isSmallerTen(mHour)+"시 "+ isSmallerTen(mMinute)+"분\n"+"인원수 : "+mPersonNum+"명 \n"+"메모 : "+mMemo+"\n\n";
        reservationConfirm.setMessage(checkMsg+"이대로 예약요청을 진행할까요?").setCancelable(false).setPositiveButton("확인",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {// 'YES'
                        saveData();
                        Toast.makeText(reservation_choose.this,"예약번호 : "+reservationNum,Toast.LENGTH_SHORT).show();
                                goIntent();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 'No'
                return;
            }
        });
        AlertDialog alert = reservationConfirm.create();
        alert.show();
    }

    private void updateData() { //변경된 날짜,시간,메모 업데이트
        String strDate = mYear + "-" + isSmallerTen(mMonth+1) + "-" + isSmallerTen(mDay); //출력할 시간 ex.2016-09-15
        String strTime;
        String strPersonNum = ""+mPersonNum;
        if (mHour<12)
            strTime = "AM "+isSmallerTen(mHour)+" : "+isSmallerTen(mMinute);
        else
            strTime = "PM "+isSmallerTen(mHour)+" : "+isSmallerTen(mMinute);
        tv_pickedDate.setText(strDate);
        tv_pickedTime.setText(strTime);
        tv_pickedPersonsNum.setText(strPersonNum);
        reservationNum = ""+(mYear-2000)+isSmallerTen(mMonth+1)+isSmallerTen(mDay)+isSmallerTen(mHour)+isSmallerTen(mMinute)+id;
        rDate = ""+mYear+isSmallerTen(mMonth+1)+isSmallerTen(mDay); //디비에 저장될 예약날짜
        rTime = isSmallerTen(mHour)+":"+isSmallerTen(mMinute); // 디비에 저장될 예약시간
        mMemo = et_memo.getText().toString();
    }

    private void goIntent(){
        Intent intent = new Intent(reservation_choose.this, customer_reservation_detail.class);
        Bundle data = new Bundle();
        data.putString("reservationNum",reservationNum);
        data.putString("id",id);
        intent.putExtras(data);
        startActivity(intent);
    }

    private void saveData() {//예약정보 db에 입력

        db.execSQL("INSERT INTO reservation VALUES('"+reservationNum+"','"+id+"','"+r_id+"','"+rDate+"','"+rTime+"','"+mPersonNum+"','no','"+mMemo+"','notYet');");

    }

    protected Dialog onCreateDialog(int idD) {//다이얼로그띄우기
        switch (idD) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener,mHour,mMinute,false);
        }
        return null;
    }
    private String isSmallerTen(int num){
        String reNum;
        if (num<10)
            reNum = "0"+num;
        else
            reNum = ""+num;
        return reNum;
    }
}



