package com.example.user_pc.lab06;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetail extends AppCompatActivity {
    TextView name, tel, email, address;
    SQLiteDatabase db;
    DBHelper2 helper;

    Button btn_edit;
    Button btn_delete;

    String preName,preTel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_problem2_itemdetail);
        helper = new DBHelper2(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }


        preName = getIntent().getExtras().getString("name");
        preTel = getIntent().getExtras().getString("tel");

        name = (TextView) findViewById(R.id.text_name_detail);
        tel = (TextView) findViewById(R.id.text_tel_detail);
        email = (TextView) findViewById(R.id.text_email_detail);
        address = (TextView) findViewById(R.id.text_address_detail);

        name.setText(preName);
        tel.setText(preTel);

        btn_edit = (Button) findViewById(R.id.btn_edit_detail);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetail.this, EditItem.class);
                Bundle data = new Bundle();

                data.putString("name",name.getText().toString());
                data.putString("tel", tel.getText().toString());

                intent.putExtras(data);

                startActivity(intent);
            }
        });

        btn_delete = (Button) findViewById(R.id.btn_delete_detail);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
                finish();
            }
        });

        // Complete codes to show name, tel, email, address using db
        // You can also get name, tel using bundle

    }

    public int delete() {
        // Complete codes..
        String whereClause = "name = ?";
        String[] whereArgs = new String[]{name.getText().toString()};
        int count = db.delete("contacts",whereClause,whereArgs);
        return count;
    }

    // You can define more methods if you need
}
