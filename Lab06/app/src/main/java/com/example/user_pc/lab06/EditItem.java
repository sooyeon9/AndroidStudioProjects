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

public class EditItem extends AppCompatActivity {
    SQLiteDatabase db;
    DBHelper2 helper;
    Button btn_update;
    Button btn_back;
    EditText edit_tel,edit_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_problem2_itemdetail_edit);
        helper = new DBHelper2(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e){
            db = helper.getReadableDatabase();
        }

        btn_update = (Button) findViewById(R.id.btn_done_detail_edit);
        edit_tel = (EditText) findViewById(R.id.edit_tel_detail_edit);
        edit_name = (EditText) findViewById(R.id.edit_name_detail_edit);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Complete code...
                // insert()를 호출할 것인지 update()를 호출할 것인지를 구현해도 좋고
                // method를 하나만 만들어서 그 안에서 db.insert를 할것인지 db.update를 할 것인지를 구현해도 좋습니다.
                // 잘 돌아가게만 하세요 ! :)

                if (search(edit_name.getText().toString())==true)
                    update(edit_name.getText().toString(),edit_tel.getText().toString());
                else
                    insert(edit_name.getText().toString(),edit_tel.getText().toString());

                finish();
            }
        });

        btn_back = (Button) findViewById(R.id.btn_back_detail_edit);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void insert(String name, String tel) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("tel",tel);
        db.insert("contacts", null, values);
        edit_name.setText("");
        edit_tel.setText("");
    }

    public int update(String oldTel, String newTel) {
        ContentValues values = new ContentValues();
        values.put("tel",newTel);
        String whereClause = "tel = ?";
        int count = db.update("contents", values, whereClause, new String[]{oldTel});
        return count;
    }

    public boolean search(String name) {
        boolean exist = true;
        Cursor cursor = db.rawQuery("SELECT * FROM contacts WHERE name = ?", new String[]{name});
        if (cursor==null && cursor.getCount()==0)
            exist = false;
        return exist;
    }

    // You can define more methods if you need
}
