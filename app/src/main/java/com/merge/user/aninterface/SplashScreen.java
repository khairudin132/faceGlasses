package com.merge.user.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mImageView = (ImageView) findViewById(R.id.kartunMuka);
        mTextView = (TextView) findViewById(R.id.appTitle);
        Animation myanimation = AnimationUtils.loadAnimation(this, R.anim.my_trans);
        mImageView.startAnimation(myanimation);
        mTextView.startAnimation(myanimation);

        final Intent i = new Intent (this, MainActivity.class);
        Thread timer = new Thread() {
            public void run () {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
