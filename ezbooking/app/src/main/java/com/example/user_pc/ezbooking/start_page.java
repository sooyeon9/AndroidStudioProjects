package com.example.user_pc.ezbooking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


public class start_page extends Activity {
    private ImageView b_start;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_page);


        b_start = (ImageView) findViewById(R.id.starting);

        b_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(start_page.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


    }

}