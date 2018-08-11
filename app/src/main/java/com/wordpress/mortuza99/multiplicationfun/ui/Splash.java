package com.wordpress.mortuza99.multiplicationfun.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wordpress.mortuza99.multiplicationfun.R;

public class Splash extends AppCompatActivity {

    private static final int delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(delay);
                    finish();
                    Intent cv = new Intent(Splash.this, MainActivity.class);
                    startActivity(cv);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }
}
