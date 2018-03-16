package com.example.user_pc.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    private Switch checking;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex3_table_layout);

        checking = (Switch) findViewById(R.id.toggle);
        table = (TableLayout) findViewById(R.id.myTable);

        checking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    table.setColumnCollapsed(1, false);
                } else {
                    table.setColumnCollapsed(1, true);
                }

            }
        });
    }
}