package com.example.russellmurray.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    TextView resultTxtVw;
    TextView pointsTxtVw;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playagainBtn;
    RelativeLayout gameLayout;
    TextView sumTxtVw;
    TextView timerTxtVw;
    ArrayList<Integer> answers = new ArrayList<Integer>(); // Declare answers array
    int locationOfCorrectAnswer;
    int score = 0;
    int numQues = 0;


    public void playAgain(final View view) {
        score = 0;
        numQues = 0;

        timerTxtVw.setText("30s");
        pointsTxtVw.setText("0/0");
        resultTxtVw.setText("");
        playagainBtn.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {  // Countdown timer for game

            @Override
            public void onTick(long l) {
                timerTxtVw.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playagainBtn.setVisibility(View.VISIBLE);
                timerTxtVw.setText("0s");
                resultTxtVw.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numQues));
            }
        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();
        answers.clear();
        int a = rand.nextInt(21); // Generate random number between 0 and 20
        int b = rand.nextInt(21);

        sumTxtVw.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4); // Create random location of correct answer on grid of 1 through 4

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            }
            else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer (View view) {
        //Log.i("Tag", (String) view.getTag());
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) { // Checking if user selected correct answer
            //Log.i("Correct", "Correct");
            score++;
            resultTxtVw.setText("Correct!");
        }
        else {
            resultTxtVw.setText("Wrong!");
        }
        numQues++;
        pointsTxtVw.setText(Integer.toString(score) + "/" + Integer.toString(numQues));
        generateQuestion();
    }

    public void start(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playagainBtn));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startBtn);

        sumTxtVw = (TextView) findViewById(R.id.sumTxtVw);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        resultTxtVw = (TextView) findViewById(R.id.resultTxtVw);
        pointsTxtVw = (TextView) findViewById(R.id.pointsTxtVw);
        timerTxtVw = (TextView) findViewById(R.id.timerTxtVw);
        playagainBtn = (Button) findViewById(R.id.playagainBtn);
        gameLayout = (RelativeLayout) findViewById(R.id.gameRelLay);
        generateQuestion();
        //playAgain(findViewById(R.id.playagainBtn));
    }
}
