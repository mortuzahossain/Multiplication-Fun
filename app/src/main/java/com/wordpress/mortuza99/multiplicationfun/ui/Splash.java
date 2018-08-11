package com.wordpress.mortuza99.multiplicationfun.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.wordpress.mortuza99.multiplicationfun.R;

public class Splash extends AppCompatActivity {

    private static final int delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView loaderImage = findViewById(R.id.loaderImage);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
     git    rotateAnimation.setDuration(1000);
        loaderImage.startAnimation(rotateAnimation);

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
