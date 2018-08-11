package com.wordpress.mortuza99.multiplicationfun.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.mortuza99.multiplicationfun.R;

public class Score extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String SHARED_NAME = "multiplicationFunSharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final String appDownloadLink = "";
        final String score = getIntent().getStringExtra("SCORE");

        // HIGH SCORE SAVE IN SHARED Preferences
        TextView highScoreText = findViewById(R.id.highScoreText);
        sharedPreferences = getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String highscore = sharedPreferences.getString("highscore", "0");
        if (Integer.parseInt(highscore) < Integer.parseInt(score)) {
            editor.putString("highscore", String.valueOf(score))
                    .apply();
            showToast("YOU MADE HIGH SCORE !!");
            highscore = score;
        }
        highScoreText.setText(highscore);


        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText(score);


        ImageButton btnShare = findViewById(R.id.btnScoreShare);
        ImageButton btnPlayAgain = findViewById(R.id.btnPlayAgain);
        ImageButton btnRatting = findViewById(R.id.btnRatting);

        btnRatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Not Implemented Yet");
            }
        });

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "I gain : " + score + " points on " + R.string.app_name + ". Download this app on : " + appDownloadLink;
                Intent intent = new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Share via"));
            }
        });


    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
