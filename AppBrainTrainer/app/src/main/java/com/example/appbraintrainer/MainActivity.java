package com.example.appbraintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int resPos;
    ArrayList<Integer> ans;
    int correct;
    int total;
    Button btn0, btn1, btn2, btn3;
    TextView tvResult;

    public void check(View v)
    {
        TextView tvNumber = findViewById(R.id.tvNumber);
        if (v != null && v.getTag().equals(resPos+"")) {
            correct++;
            tvResult.setTextColor(Color.GREEN);
            tvResult.setText("CORRECT!");
        }
        else
        {
            tvResult.setTextColor(Color.RED);
            tvResult.setText("Wrong!");
        }

        total++;

        tvNumber.setText("Correct: " + correct + "/" +total);
        createQuestion();
    }

    public void start(View v)
    {
        correct = 0;
        total = 0;
        findViewById(R.id.btnStart).setVisibility(View.INVISIBLE);
        findViewById(R.id.layoutPlaying).setVisibility(View.VISIBLE);
        tvResult.setText("");

        final TextView tvCountDown = findViewById(R.id.tvCountDown);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished)
            {
                tvCountDown.setText("Remaining: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish()
            {
                Toast.makeText(MainActivity.this, "You got "+correct + "/" +total +" correct answers!", Toast.LENGTH_LONG).show();
                findViewById(R.id.btnStart).setVisibility(View.VISIBLE);
                findViewById(R.id.layoutPlaying).setVisibility(View.INVISIBLE);
            }

        }.start();

        createQuestion();
    }

    public void createQuestion()
    {
        // set up a maths
        Random rd = new Random();
        int numberOne = rd.nextInt(100);
        int numberTwo = rd.nextInt(100);
        //char [] signs = {'+','-','*','/'};
        //int sIndex = rd.nextInt(4);
        //String maths = numberOne + " " + signs[sIndex] + " " + numberTwo;
        String maths =numberOne + " + " + numberTwo;
        TextView textView = findViewById(R.id.textView);
        textView.setText(maths);

        // create a variable for carrying the result
        //int result = 0;
        int result = numberOne + numberTwo;
        /*
        switch (signs[sIndex])
        {
            case '+':
                result = numberOne + numberTwo;
                break;
            case '-':
                result = numberOne - numberTwo;
                break;
            case '*':
                result = numberOne * numberTwo;
                break;
            case '/':
                while (numberTwo == 0)
                {
                    rd.nextInt(100);
                }
                result = numberOne / numberTwo;
                break;
        }
        */

        // pick up 1 random position for true result


        resPos = rd.nextInt(4);
        ans.clear();
        int positiveRes = result <= 0 ? result * -1 + 10 : result;
        for (int i = 0; i < 4; i++)
        {
            if (i != resPos)
            {
                int number = rd.nextInt(positiveRes);
                while (ans.contains(number))
                {
                    number = rd.nextInt(positiveRes);
                }

                if (new Random().nextInt(2) == 1)
                {
                    ans.add(number*-1);
                }
                else
                {
                   ans.add(number);
                }
            }
            else
            {
                ans.add(result);
            }
        }

        btn0.setText(Integer.toString(ans.get(0)));
        btn1.setText(Integer.toString(ans.get(1)));
        btn2.setText(Integer.toString(ans.get(2)));
        btn3.setText(Integer.toString(ans.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        ans = new ArrayList<>();

        tvResult =  findViewById(R.id.tvResult);
    }
}
