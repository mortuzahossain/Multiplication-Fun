package com.wordpress.mortuza99.multiplicationfun;

import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int seconds = 60;
    private TextView timeText, scoreText, questionText, ansText;

    Button btnRight, btnWrong;

    private boolean isResultCorrect;
    private int score = 0;
    private boolean stopTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        questionText = findViewById(R.id.questionText);
        ansText = findViewById(R.id.ansText);
        btnRight = findViewById(R.id.btnRight);
        btnWrong = findViewById(R.id.btnWrong);

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        timer();
        generateRandomQuestion();

    }

    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                seconds--;
                String time = getString(R.string.time_30s) + String.valueOf(seconds) + "s";
                timeText.setText(time);
                if (seconds < 0) {
                    startActivity(new Intent(MainActivity.this, Score.class)
                            .putExtra("SCORE", String.valueOf(score))
                    );
                    stopTimer = true;
                }
                if (!stopTimer) {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    // Generating Question
    private void generateRandomQuestion() {
        isResultCorrect = true;
        Random random = new Random();
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        int result = a * b;
        float f = random.nextFloat();
        if (f > 0.5f) {
            result = random.nextInt(100);
            isResultCorrect = false;
        }
        questionText.setText(String.format("%s * %s", String.valueOf(a), String.valueOf(b)));
        ansText.setText(String.format("= %s", String.valueOf(result)));
    }

    private void checkAnswer(boolean answer) {
        if (answer == isResultCorrect) {
            score += 5;
        } else {
            Vibrator vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        scoreText.setText(String.format("%s%s", getString(R.string.score_5), String.valueOf(score)));
        generateRandomQuestion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer = false;
        finish();
    }
}
