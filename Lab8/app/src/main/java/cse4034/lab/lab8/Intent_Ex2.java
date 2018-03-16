package cse4034.lab.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Intent_Ex2 extends Activity {

    /*
     * Default parameters
     */
    private String defaultParam1 = "Hello, World";
    private int defaultParam2 = 4034;
    private int[] defaultParam3;    // Will be filled in from integer-array Resource

    private EditText view_Param1;    // String param.
    private EditText view_Param2;    // Integer param.
    private EditText view_Param3;    // Integer array param.

    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

			/* 
			 * Show default parameters
			 */
        view_Param1 = (EditText) findViewById(R.id.string_param);
        view_Param1.setText(defaultParam1);

        view_Param2 = (EditText) findViewById(R.id.int_param);
        view_Param2.setText(String.valueOf(defaultParam2));

        view_Param3 = (EditText) findViewById(R.id.array_param);
        defaultParam3 = getResources().getIntArray(R.array.int_array);
        view_Param3.setText(Utility.toString(defaultParam3));

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				/*
				 * Read String parameter
				 */
                String param1 = view_Param1.getText().toString();

				/*
				 * Read Integer parameter
				 */
                int param2 = Integer.valueOf(view_Param2.getText().toString());

				/*
				 * Read Int array parameter
				 */
                int[] param3 = Utility.getIntArray(view_Param3.getText().toString());

				/*
				 * To-Do
				 */
                Intent intent = new Intent(Intent_Ex2.this, SubActivity2.class);
                intent.putExtra("String",param1);
                intent.putExtra("Integer",param2);
                intent.putExtra("IntArray",param3);

                startActivity(intent);

				/*
				 * 1. Create Intent to call SubActivity2.
				 * 2. Put parameters into Intent using only Intent.putExtra(...)
				 * 3. Invoke SubActivity2 using startActivity()
				 *
				 * Use "Param1", "Param2", and "Param3" as keys for successive parameters.
				 * For example, putExtra("Param1", value)
				 */

            }
        });
    }
}
