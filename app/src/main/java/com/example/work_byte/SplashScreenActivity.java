package com.example.work_byte;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView splash_logo, splash_w;

    //logo animation
    ScaleAnimation scaleInAnimation, scaleOutAnimation;
    TranslateAnimation translateLogo;
    AnimationSet setLogo;

    //text animation
    AnimationSet animSetTextW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splash_logo = findViewById(R.id.splashlogo);
        splash_w = findViewById(R.id.splashW);


        //logo animation
        scaleInAnimation = new ScaleAnimation(1,1f,1,1f,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleInAnimation.setDuration(800);
        scaleInAnimation.setFillAfter(true);

        scaleOutAnimation = new ScaleAnimation(1f,1,1f,1,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleOutAnimation.setDuration(800);
        scaleOutAnimation.setFillAfter(true);

        translateLogo = new TranslateAnimation(0,-177,0,0);
        translateLogo.setDuration(800);

        setLogo = new AnimationSet(true);
        setLogo.addAnimation(scaleOutAnimation);
        setLogo.addAnimation(translateLogo);
        setLogo.setFillAfter(true);



        //text animation
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(800);

        TranslateAnimation transW = new TranslateAnimation(0,57,0,0);
        transW.setDuration(800);


        animSetTextW = new AnimationSet(true);
        animSetTextW.addAnimation(alphaAnimation);
        animSetTextW.addAnimation(transW);
        animSetTextW.setFillAfter(true);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splash_logo.startAnimation(scaleInAnimation);
            }
        },2000);




        scaleInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                splash_logo.startAnimation(setLogo);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });

        setLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                splash_w.startAnimation(animSetTextW);

                splash_w.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}