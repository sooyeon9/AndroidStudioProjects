package cse4034.lab.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Intent_Ex3 extends Activity {

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
        setContentView(R.layout.activity_main3);

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
                Intent intent = new Intent(Intent_Ex3.this, SubActivity3.class);
                Bundle data = new Bundle();

                data.putString("param1",param1);
                data.putInt("param2",param2);
                data.putIntArray("param3",param3);

                intent.putExtras(data);
                startActivity(intent);

					/*
					 * This exercise is essentially the same as Intent_Ex2
					 * except the fact of using Bundle explicitly for parameter passing.
					 *
					 * 1. Create Intent to call SubActivity3.
					 * 2. Create a Bundle.
					 * 3. Put parameters into Bundle using Intent.putString(), putInt(), putIntArray().
					 * 4. Put the Bundle into the Intent created in step 1 using Intent.putExtras().
					 * 5. Invoke SubActivity3 using startActivity()
					 *
					 * Use "Param1", "Param2", and "Param3" as keys for successive parameters.
					 * For example, putString("Param1", value)
					 */

            }
        });

    }
}
