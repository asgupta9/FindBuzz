package com.example.findbuzz.findbuzz;


import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ActivitySplashScreen extends AppCompatActivity {

    long Delay = 1000;
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
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(ActivitySplashScreen.this);
                // Check if we need to display our OnboardingFragment
                if (!sharedPreferences.getBoolean(
                        "COMPLETED_ONBOARDING_PREF_NAME", false)) {
                    Intent myIntent = new Intent(ActivitySplashScreen.this,
                            ActivityOnBoarding.class);
                    startActivity(myIntent);
                }
                else{

                    SharedPreferences sharedPref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                    String isloggedin= sharedPref.getString("isloggedin","");

                    Log.d("TAG","loggedin"+isloggedin);

//                    Toast.makeText(ActivitySplashScreen.this, "", Toast.LENGTH_SHORT).show();

                    if(isloggedin=="1"){

                        Intent myIntent = new Intent(ActivitySplashScreen.this,
                                ActivityHome.class);
                        startActivity(myIntent);


                    }
                    else{

                        Intent myIntent = new Intent(ActivitySplashScreen.this,
                                Activity_user_login.class);
                        startActivity(myIntent);

                    }

                }
            }
        };
        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }
}
