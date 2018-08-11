package com.wordpress.mortuza99.multiplicationfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final String appDownloadLink = "";
        final String score = getIntent().getStringExtra("SCORE");
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
