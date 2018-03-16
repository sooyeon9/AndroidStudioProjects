package com.example.user_pc.lab06;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Problem1 extends AppCompatActivity {
    DBHelper1 helper;
    SQLiteDatabase db;
    Button btn_insert;
    Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_problem1);
        helper = new DBHelper1(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e){
            db = helper.getReadableDatabase();
        }

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_search = (Button) findViewById(R.id.btn_search);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    public void insert() {
        EditText edit_name = ((EditText)findViewById(R.id.edit_name));
        EditText edit_tel = ((EditText)findViewById(R.id.edit_tel));
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("tel",tel);
        db.insert("contacts",null, values);
        edit_name.setText("");
        edit_tel.setText("");
    }

    public void search() {
        String edit_name = ((EditText)findViewById(R.id.edit_name)).getText().toString();
        EditText edit_tel = ((EditText)findViewById(R.id.edit_tel));
        Cursor cursor;
        cursor = db.query("contacts",new String[] {"tel"},"name = ?", new String[] {edit_name}, null, null, null);
//        cursor = db.query("contacts",null,"name = ?", new String[] {edit_name}, null, null, null);
//        cursor = db.query("contacts",new String[] {"*"},"name = ?", new String[] {edit_name}, null, null, null);
        while (cursor.moveToNext()) {
            String tel = cursor.getString(0); // columns에 직접 지정했을 경우 columns[] 의 index에 해당하는 column을 반환한다.
//            String tel = cursor.getString(2); // column을 모두 반환하려면 columns[]에 null을 넣거나 new String[] {"*"} 넣는다. attribute의 index를 넣는다
            edit_tel.setText(tel);
        }
    }
}