package cse4034.lab.lab8;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubActivity1 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity1);

        ((TextView) findViewById(R.id.name)).setText(getClass().getSimpleName());
    }

    // onClick Handler for finish button
    public void onFinish(View v) {
        finish();
    }

}
