package com.example.user_pc.ezbooking;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    public static AppCompatActivity loginActivity;
    private EditText et_Id, et_Pw;
    private Button b_Login, b_Join;
    private RadioGroup radioGroup;
    private RadioButton selected;
    //private RadioButton rb_Customer, rb_Restaurant;
    private String Id, Pw, loginType="C";
    SQLiteDatabase db;
    DBhelper helper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        loginActivity = Login.this;

        helper = new DBhelper(this);

        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        et_Id = (EditText) findViewById(R.id.editId);
        et_Pw = (EditText) findViewById(R.id.editPw);
        b_Login = (Button) findViewById(R.id.buttonLogin);
        b_Join = (Button) findViewById(R.id.buttonJoin);
        //rb_Customer = (RadioButton) findViewById(R.id.radioButtonCustomer);
        //rb_Restaurant = (RadioButton) findViewById(R.id.radioButtonRestaurant);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);


        b_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ;//when click button [login]

                //db.execSQL("DROP TABLE customer");
                //db.execSQL("CREATE TABLE customer( id TEXT,pw TEXT, phone TEXT, name TEXT, fame INTEGER, point INTEGER, email TEXT);");
                //db.execSQL("INSERT INTO customer VALUES('ksy','123','01000000000','구수연',3,1000,'ksy@naver.com');");

                String check_pw = "";
                Id = et_Id.getText().toString();
                Pw = et_Pw.getText().toString();
                if(loginType.equals("R")){
                    Cursor c = db.rawQuery("SELECT pw FROM restaurant where r_id= ? ", new String[]{Id});
                    while (c.moveToNext()) {
                        check_pw = c.getString(0);
                    }
                    if(checkingPw(check_pw)){
                        //Restaurant 회원 로그인 이후 화면 intent
                        Intent intent = new Intent(Login.this, restaurant_page.class);
                        Bundle data = new Bundle();
                        data.putString("r_id",Id);
                        intent.putExtras(data);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호 오류입니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Cursor c = db.rawQuery("SELECT pw FROM customer where id= ? ", new String[]{Id});
                    while (c.moveToNext()) {
                        check_pw = c.getString(0);
                    }
                    if(checkingPw(check_pw)){
                        //Customer 회원 로그인 이후 화면 intent
                        Intent intent = new Intent(Login.this, mypage.class);
                        Bundle data = new Bundle();
                        data.putString("id",Id);
                        intent.putExtras(data);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호 오류입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        b_Join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //when click button [join]
                //intent에 loginType 담아서 날린다
                if(loginType.equals("R")){
                    Intent intent = new Intent(Login.this, SignRestaurant.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Login.this, SignCustomer.class);
                    startActivity(intent);
                }

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonCustomer:
                        Toast.makeText(getApplicationContext(), "일반회원", Toast.LENGTH_LONG).show();
                        loginType = "C";
                        break;
                    case R.id.radioButtonRestaurant:
                        Toast.makeText(getApplicationContext(), "가게회원", Toast.LENGTH_LONG).show();
                        loginType = "R";
                        break;
                }
            }
        });


    }

    public Boolean checkingPw(String pw){
        Boolean result=false;
        if(pw.equals(Pw))
            result = true;
        return result;
    }



}
