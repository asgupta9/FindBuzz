package com.example.findbuzz.findbuzz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;

public class ActivitySplashScreen extends AppCompatActivity {

    long Delay = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Timer RunSplash=new Timer();

        TimerTask ShowSplash=new TimerTask() {
            @Override
            public void run() {

                finish();
                // Start MainActivity.class

                Intent myIntent = new Intent(ActivitySplashScreen.this,
                        ActivityOnBoarding.class);
                startActivity(myIntent);
            }
        };
        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }
}
