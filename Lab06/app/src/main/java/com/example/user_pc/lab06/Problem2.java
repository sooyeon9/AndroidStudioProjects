package com.example.user_pc.lab06;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Problem2 extends AppCompatActivity {

    ListView listview;
    Adapter adapter;
    Button btn_add;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_problem2_main);

        adapter = new Adapter();

        listview = (ListView) findViewById(R.id.list_item);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Problem2.this, ItemDetail.class);
                Bundle data = new Bundle();

                // 파라미터로 넘어온 view 는 Click한 item 입니다. (setOnItemClickListener 라는 걸 보고 유추 할 수 있겠죠?)
                // 고로 name, tel의 값은 아래와 같이 구할 수 있습니다.

                String name = ((TextView) view.findViewById(R.id.list_name)).getText().toString();
                String tel = ((TextView) view.findViewById(R.id.list_tel)).getText().toString();

                data.putString("name",name);
                data.putString("tel", tel);

                intent.putExtras(data);
                startActivity(intent);
            }
        });

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Problem2.this, EditItem.class);
                startActivity(intent);
            }
        });

        // Add items.. (name, value) pair
        adapter.addItem("Bof","1234"); // example
        // maybe use while loop..

        // ** 이름 순으로 나타나게 구현할 것 ! **
    }

    // You can define more methods if you need

}
