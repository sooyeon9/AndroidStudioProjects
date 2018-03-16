package cse4034.lab.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity2 extends Activity {

    private EditText view_Param1;    // String param.
    private EditText view_Param2;    // Integer param.
    private EditText view_Param3;    // Integer array param.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity2);

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
		
		/*
		 * 1. Get Intent using getIntetnt()
		 * 2. Read string parameter from intent 
		 *    using Intent.getStringExtra() and display it.
		 * 3. Read integer parameter from intent 
		 *    using Intent.getIntExtra() and display it.
		 * 4. Read integer-array parameter from intent 
		 *    using Intent.getIntArrayExtra() and display it.
		 * 
		 * The keys for three parameters are 
		 * "Param1", "Param2", and "Param3".
		 * You can use Utility.toSting() to format 
		 * int-array to string for display.
		 */
        Intent intent = getIntent();
        view_Param1.setText(intent.getStringExtra("String"));
        view_Param2.setText(intent.getIntExtra("Integer", 0));
        view_Param3.setText(intent.getIntArrayExtra("IntArray").toString());

    }

    // onClick Handler for finish button
    public void onFinish(View v) {
        finish();
    }
}
