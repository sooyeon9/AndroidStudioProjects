package com.example.user_pc.ezbooking;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SignRestaurant extends AppCompatActivity {
    private EditText et_Id, et_Pw, et_Pw2, et_Bnum, et_Name, et_Mail, et_Phone;
    private Button b_Sign, b_Confirm, b_CheckIdentify;
    private String Id, Pw, Pw2, Name, Mail, Bnum, Phone;
    private Boolean idCheck = false;
    SQLiteDatabase db;
    DBhelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signrestaurant2);
        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        et_Id = (EditText) findViewById(R.id.editId);
        et_Pw = (EditText) findViewById(R.id.editPw);
        et_Pw2 = (EditText) findViewById(R.id.editPw2);
        et_Bnum = (EditText) findViewById(R.id.editBnum);
        et_Mail = (EditText) findViewById(R.id.editMail);
        et_Name = (EditText) findViewById(R.id.editName);
        et_Phone = (EditText) findViewById(R.id.editPhone);
        b_Sign = (Button) findViewById(R.id.sign);
        b_Confirm = (Button) findViewById(R.id.confirm);
        b_CheckIdentify = (Button) findViewById(R.id.checkIdentify);


        b_CheckIdentify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ; //when click button [checkIdentify]
                Id = et_Id.getText().toString();
                Cursor c = db.rawQuery("SELECT r_id FROM restaurant where r_id= ? ", new String[]{Id});
                String check_id = "";
                while (c.moveToNext()) {
                    check_id = c.getString(0);
                }
                if(check_id.equals("")){
                    Toast.makeText(getApplicationContext(), "사용가능. 중복된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                    idCheck = true;
                }else{
                    Toast.makeText(getApplicationContext(), "사용불가. 중복된 아이디가 있습니다.", Toast.LENGTH_SHORT).show();
                    idCheck = false;
                }

            }
        });

        b_Sign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ; //when click button [sign]
                Id = et_Id.getText().toString();
                Pw = et_Pw.getText().toString();
                Pw2 = et_Pw2.getText().toString();
                Mail = et_Mail.getText().toString();
                Name = et_Name.getText().toString();
                Bnum = et_Bnum.getText().toString();
                Phone = et_Phone.getText().toString();

                if(idCheck){
                    if(pwCheck()){
                        String sql = ("INSERT INTO restaurant VALUES('"+Id+"','"+Pw+"','"+Phone+"','"+Bnum+"','"+Name+"',0,'NULL','NULL','NULL','NULL','"+Mail+"','NULL','NULL','NULL','NULL');");

                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "환영합니다!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignRestaurant.this, restaurant_page.class);
                        Bundle data = new Bundle();
                        data.putString("r_id",Id);
                        intent.putExtras(data);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        b_Confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ; //when click button [confirm]
                Bnum = et_Bnum.getText().toString();
            }
        });

    }

    public Boolean pwCheck(){
        if(Pw.equals(Pw2))
            return true;
        else
            return false;
    }

}
