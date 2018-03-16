package cse4034.lab.lab8;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity3 extends Activity {

    private EditText view_Param1;    // String param.
    private EditText view_Param2;    // Integer param.
    private EditText view_Param3;    // Integer array param.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity3);

        ((TextView) findViewById(R.id.name)).setText(getClass().getSimpleName());

        view_Param1 = (EditText) findViewById(R.id.string_param);

        view_Param2 = (EditText) findViewById(R.id.int_param);

        view_Param3 = (EditText) findViewById(R.id.array_param);

        processInput();

    }

    private void processInput() {

		/*
		 * To-Do
		 */
		
		/* This exercise is essentially the same as the SubActivity2 
		 * except the fact of using Bundle explicitly for parameter passing.
		 * 
		 * 1. Get Intent using getIntetnt()
		 * 2. Retrieve the Bundle from the Intent.
		 * 3. Read the string parameter from Bundle 
		 * 	  using Bundle.getString() and display it.
		 * 4. Read the integer parameter from Bundle 
		 *    using Bundle.getInt() and display it.
		 * 5. Read integer-array parameter from Bundle 
		 *    using Bundle.getIntArray() and display it.
		 * 
		 * The keys for three parameters are "Param1", "Param2", and "Param3".
		 * You can use Utility.toSting() to format int-array to string for display.
		 */
        view_Param1.setText(getIntent().getExtras().getString("param1"));
        view_Param2.setText(getIntent().getExtras().getInt("param2"));
        view_Param3.setText(getIntent().getExtras().getIntArray("param3").toString());



    }

    // onClick Handler for finish button
    public void onFinish(View v) {
        finish();
    }
}
