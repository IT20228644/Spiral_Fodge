package com.example.work_byte;

import android.content.Intent;
import android.content.res.Configuration;
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


        String text;

        //determine the screen size
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE){
            text = "LargeScreen";
        }


        //logo animation
        scaleInAnimation = new ScaleAnimation(1,35f,1,35f,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleInAnimation.setDuration(1200);
        scaleInAnimation.setFillAfter(true);

        scaleOutAnimation = new ScaleAnimation(35f,1,35f,1,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleOutAnimation.setDuration(1200);
        scaleOutAnimation.setFillAfter(true);

        translateLogo = new TranslateAnimation(0,-300,0,0);
        translateLogo.setDuration(1200);

        setLogo = new AnimationSet(true);
        setLogo.addAnimation(scaleOutAnimation);
        setLogo.addAnimation(translateLogo);
        setLogo.setFillAfter(true);



        //text animation
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(1200);

        TranslateAnimation transW = new TranslateAnimation(0,120,0,0);
        transW.setDuration(1200);


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

                splash_logo.startAnimation(setLogo);
                splash_logo.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {



            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });

        setLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {



            }

            @Override
            public void onAnimationEnd(Animation animation) {

                splash_w.startAnimation(animSetTextW);

                splash_w.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(in);
                    }
                },2500);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}