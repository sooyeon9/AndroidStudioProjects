package com.example.user_pc.ezbooking;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;


public class SignCustomer extends AppCompatActivity {

    private EditText et_Id, et_Pw, et_Pw2, et_Phone, et_Name, et_Mail;
    private Button b_Sign, b_Confirm, b_CheckIdentify;
    private String Id, Pw, Pw2, Name, Mail, Phone;
    private Boolean idCheck = false;
    SQLiteDatabase db;
    DBhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signcustomer2);
        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        et_Id = (EditText) findViewById(R.id.editId);
        et_Pw = (EditText) findViewById(R.id.editPw);
        et_Name = (EditText) findViewById(R.id.editName);
        et_Pw2 = (EditText) findViewById(R.id.editPw2);
        et_Phone = (EditText) findViewById(R.id.editPhone);
        et_Mail = (EditText) findViewById(R.id.editMail);
        b_Sign = (Button) findViewById(R.id.sign);
        b_Confirm = (Button) findViewById(R.id.confirm);
        b_CheckIdentify = (Button) findViewById(R.id.checkIdentify);


        b_CheckIdentify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //when click button [checkIdentify]

                Id = et_Id.getText().toString();
                Cursor c = db.rawQuery("SELECT id FROM customer where id= ? ", new String[]{Id});
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
                //when click button [회원가입]
                //1.비밀번호체크
                //2.이메일형식체크
                Id = et_Id.getText().toString();
                Pw = et_Pw.getText().toString();
                Pw2 = et_Pw2.getText().toString();
                Mail = et_Mail.getText().toString();
                Name = et_Name.getText().toString();
                Phone = et_Phone.getText().toString();

                if(idCheck){
                    if(pwCheck()){
                        String sql = ("INSERT INTO customer VALUES('"+Id+"','"+Pw+"','"+Phone+"','"+Name+"',1,0,'"+Mail+"');");
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "환영합니다!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignCustomer.this, SelectFirst.class);
                        Bundle data = new Bundle();
                        data.putString("id",Id);
                        intent.putExtras(data);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                    /*
                    if(pwCheck()&&mailCheck()){
                        String sql = ("INSERT INTO customer VALUES('"+Id+"','"+Pw+"','"+Phone+"','"+Name+"',1,0,'"+Mail+"');");
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "환영합니다!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignCustomer.this, SelectFirst.class);
                        Bundle data = new Bundle();
                        data.putString("id",Id);
                        intent.putExtras(data);
                        startActivity(intent);

                    } else if((!pwCheck())&&mailCheck()) {
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else if(pwCheck()&&(!mailCheck())) {
                        Toast.makeText(getApplicationContext(), "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호 불일치 및 잘못된 이메일 형식입니다.", Toast.LENGTH_SHORT).show();
                    }*/
                } else {
                    Toast.makeText(getApplicationContext(), "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        b_Confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //when click button [인증]
                Phone = et_Phone.getText().toString();

            }
        });
    }


    public Boolean pwCheck(){
        if(Pw.equals(Pw2))
            return true;
        else
            return false;
    }


    /*
    public Boolean mailCheck(){
        Pattern p = Pattern.compile("^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]");
        Matcher m = p.matcher(Mail);
        if (!m.matches()) {
            return false;
        } else {
            return true;
        }
    }
    */


}
