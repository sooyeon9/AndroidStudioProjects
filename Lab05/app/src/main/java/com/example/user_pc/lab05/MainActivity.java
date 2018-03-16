package com.example.user_pc.lab05;


        import android.content.res.TypedArray;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText sumView;
    private RadioGroup radioGroup;
    private ImageView imageView;

    private TypedArray planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex3_gridlayout);

        sumView = (EditText) findViewById(R.id.sumView);
        imageView = (ImageView) findViewById(R.id.imageView);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                handleRadioGroupSelection(checkedId);
            }
        });

        processIntArray();

        processImages();
    }

    private void processIntArray() {
        int[] primes = getResources().getIntArray(R.array.primes); // Read integer array R.array.primes
        int sum=0;
        for(int i=0; i<primes.length; i++)
            sum += primes[i];
        sumView.setText(sum);
    }

    private void processImages() {

        planets = getResources().obtainTypedArray(R.array.planets);  // // Read integer array R.array.planets as TypedArray

        imageView.setImageResource(planets.getResourceId(0, 0)); // display the first view as default
    }

    private void handleRadioGroupSelection(int checkedId) {
        int index = 0;
        switch(checkedId) {
            case R.id.sun:
                index = 0; break;
            case R.id.mercury:
                index = 1; break;
            case R.id.venus:
                index = 2; break;
            case R.id.earth:
                index = 3; break;
            case R.id.mars:
                index = 4; break;
        }

       /*
         Display the selected image on imageView
       */
        imageView.setImageResource(planets.getResourceId(0,3));
    }
}

