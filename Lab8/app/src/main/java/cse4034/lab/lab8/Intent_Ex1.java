package cse4034.lab.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intent_Ex1 extends Activity {
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * To-Do
		        /*
                 * Create Intent to call SubActivity1 and invoke SubActivity1
		         */
                Intent intent = new Intent(Intent_Ex1.this, SubActivity1.class);
                startActivity(intent);


            }
        });
    }
}
