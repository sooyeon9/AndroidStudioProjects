package com.example.user_pc.ezbooking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by SOPHIE on 2016. 8. 12..
 */
public class restaurant_edit_info extends AppCompatActivity {

    private RadioGroup edit_radioGroup;
    private EditText et_restInfo, et_restName,et_restPhone,et_restDo,et_restGu,et_restOther,et_restOpen,et_restClose,et_restHoliday;
    private ImageView restPhoto;
    private Button b_save;
    private Integer fame_limit=1;
    private String r_id, RestName, RestInfo, RestPhone,RestDo,RestGu,RestOther,RestOpen,RestClose,RestHoliday;
    SQLiteDatabase db;
    DBhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_edit_info);
        r_id = getIntent().getExtras().getString("r_id");

        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }
        edit_radioGroup = (RadioGroup) findViewById(R.id.rei_radiogroup);



        et_restInfo = (EditText) findViewById(R.id.et_restInfo);
        et_restName = (EditText) findViewById(R.id.et_restName);
        et_restPhone = (EditText) findViewById(R.id.et_restPhone);
        et_restDo = (EditText) findViewById(R.id.et_restDo);
        et_restGu = (EditText) findViewById(R.id.et_restGu);
        et_restOther = (EditText) findViewById(R.id.et_restOther);
        et_restOpen = (EditText) findViewById(R.id.et_restOpen);
        et_restClose = (EditText) findViewById(R.id.et_restClose);
        et_restHoliday = (EditText) findViewById(R.id.et_restHoliday);

        Log.d("DD", "SELECT");
        Cursor c = db.rawQuery("SELECT name,phone,info,Do_Si_address,Gu_address,other_address,holiday,open,close FROM restaurant where r_id= ? ", new String[]{r_id});
        String name = "";
        String phone = "";
        String info = "";
        String Do_Si_address = "";
        String Gu_address = "";
        String other_address = "";
        String holiday = "";
        String open = "";
        String close = "";

        while(c.moveToNext()){
            name=c.getString(0);
            phone=c.getString(1);
            info=c.getString(2);
            Do_Si_address=c.getString(3);
            Gu_address=c.getString(4);
            other_address=c.getString(5);
            holiday=c.getString(6);
            open=c.getString(7);
            close=c.getString(8);
        }

        et_restName.setText(name);
        et_restPhone.setText(phone);
        et_restInfo.setText(info);
        et_restDo.setText(Do_Si_address);
        et_restGu.setText(Gu_address);
        et_restOther.setText(other_address);
        et_restHoliday.setText(holiday);
        et_restOpen.setText(open);
        et_restClose.setText(close);




        b_save = (Button) findViewById(R.id.b_save);

        restPhoto = (ImageView) findViewById(R.id.restPhoto);
        int imageId = getResources().getIdentifier(r_id.toLowerCase(Locale.getDefault()),
                "drawable", this.getPackageName());
        restPhoto.setImageResource(imageId);
        edit_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.one:
                        fame_limit=1;
                        break;
                    case R.id.two:
                        fame_limit=2;
                        break;
                    case R.id.three:
                        fame_limit=3;
                        break;
                    case R.id.four:
                        fame_limit=4;
                        break;
                    case R.id.five:
                        fame_limit=5;
                        break;
                }
            }
        });

        b_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                RestInfo = et_restInfo.getText().toString();
                RestName = et_restName.getText().toString();
                RestPhone=et_restPhone.getText().toString();
                RestDo=et_restDo.getText().toString();
                RestGu=et_restGu.getText().toString();
                RestOther=et_restOther.getText().toString();
                RestOpen=et_restOpen.getText().toString();
                RestClose=et_restClose.getText().toString();
                RestHoliday=et_restHoliday.getText().toString();

                String sql = "update restaurant set name = '" + RestName +"' where r_id = '"+r_id +"';";
                db.execSQL(sql);
                String sql2 = "update restaurant set Do_Si_address = '" + RestDo +"' where r_id = '"+r_id +"';";
                db.execSQL(sql2);
                String sql3 = "update restaurant set Gu_address = '" + RestGu +"' where r_id = '"+r_id +"';";
                db.execSQL(sql3);
                String sql5 = "update restaurant set other_address = '" + RestOther +"' where r_id = '"+r_id +"';";
                db.execSQL(sql5);
                String sql6 = "update restaurant set holiday = '" + RestHoliday +"' where r_id = '"+r_id +"';";
                db.execSQL(sql6);
                String sql7 = "update restaurant set open = '" + RestOpen +"' where r_id = '"+r_id +"';";
                db.execSQL(sql7);
                String sql8 = "update restaurant set close = '" + RestClose +"' where r_id = '"+r_id +"';";
                db.execSQL(sql8);
                String sql4 = "update restaurant set info = '" + RestInfo +"' where r_id = '"+r_id +"';";
                db.execSQL(sql4);
                String sql9 = "update restaurant set fame_limit = " + fame_limit+" where r_id = '"+r_id +"';";
                db.execSQL(sql9);
                String sql10 = "update restaurant set phone = '" + RestPhone +"' where r_id = '"+r_id +"';";
                db.execSQL(sql10);
                Toast.makeText(getApplicationContext(), "저장 완료!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}