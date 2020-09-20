package com.example.checkmyhealth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final ImageView SplashIcon=(ImageView) findViewById(R.id.imageView);
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.rotate);
        anim.setDuration(3000);
        SplashIcon.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                mPlayer=MediaPlayer.create(SplashScreenActivity.this,R.raw.welcome);
                mPlayer.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(mPlayer!=null)
                {
                    mPlayer.stop();
                    mPlayer.reset();
                    mPlayer.release();
                }
                Intent i = new Intent(SplashScreenActivity.this,MenuActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
