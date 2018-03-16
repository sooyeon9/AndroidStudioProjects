package cse4034.com.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class QualificationV1 extends AppCompatActivity {

    // Declare variables for Views
    private Button leftButton;
    private Button rightButton;

    private int[] numbers = new int[2]; // number[0] = left, number[1] = right

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dojeon_layout);

        /* Get references for buttons */
        leftButton = (Button) findViewById(R.id.leftButton);
        rightButton = (Button) findViewById(R.id.rightButton);

        /* Register anonymous OnClickListener to "leftButton" */
        leftButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                                 showResult(evaluate(numbers[0], numbers[1]));
                                 startGame();
                    }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
                     public void onClick(View v){
                                 showResult(evaluate(numbers[1], numbers[0]));
                                 startGame();
                     }
        });

        startGame();
    }

    private void startGame() {
        generateTwoNumbers(numbers);
        showTwoNumbers();
    }

    private void showTwoNumbers() {
        leftButton.setText(String.valueOf(numbers[0]));
        rightButton.setText(String.valueOf(numbers[1]));
    }

    private void showResult(boolean flag) {
        if (flag) {
            Toast.makeText(getApplicationContext(), "Congratulation", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Stupid",Toast.LENGTH_SHORT).show();
            /*
             Create toast for "Stupid"
             */
        }
    }

    private boolean evaluate(int big, int small) {
        return big > small ? true : false;
    }

    private void generateTwoNumbers(int[] numbers) {
        Random random = new Random(System.currentTimeMillis());

        numbers[0] = random.nextInt(100);

        do {
            numbers[1] = random.nextInt(100);
        } while (numbers[0] == numbers[1]);
    }
}
