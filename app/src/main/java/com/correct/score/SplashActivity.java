package com.correct.score;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.correct.score.onboarding.OnboardingActivity;


public class SplashActivity extends AppCompatActivity {
    TextView tvsub;
    Animation top, bottom,slideup,leftto_right,bounce;
    SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
        loadContent();
    }

    private void initView() {
//
//        tvsub=findViewById(R.id.tvsub);
//        top = AnimationUtils.loadAnimation(this, R.anim.bottom);
//        bottom = AnimationUtils.loadAnimation(this, R.anim.top);
//        slideup = AnimationUtils.loadAnimation(this, R.anim.slide_in);
//        leftto_right = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
//        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
//        tvsub.setAnimation(bounce);

    }

    private void loadContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);
                if (isFirstTime){
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), OnboardingActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent1 = new Intent(getApplicationContext(), FullTimeActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }
        },1800);
    }
}

