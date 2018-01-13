package com.example.findbuzz.findbuzz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by akash on 11/1/18.
 */

public class ActivityOnBoarding extends AppCompatActivity {

    private ViewPager mySlideViewPager;
    private LinearLayout myLinearLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private int nDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //INITIALISATION

        mySlideViewPager=(ViewPager) findViewById(R.id.slideViewLayout);
        myLinearLayout=(LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter=new SliderAdapter(this);
        nDots=sliderAdapter.getCount();
        mySlideViewPager.setAdapter(sliderAdapter);

        mySlideViewPager.addOnPageChangeListener(viewListener);
        addDotsIndicator(0);


        final Button button = findViewById(R.id.skip_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                //Toast.makeText(ActivityOnBoarding.this, "fgfd", Toast.LENGTH_SHORT).show();
                // shared preference change


                SharedPreferences.Editor sharedPreferencesEditor =
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                sharedPreferencesEditor.putBoolean(
                        "COMPLETED_ONBOARDING_PREF_NAME", true);
                sharedPreferencesEditor.apply();

                Intent intent=new Intent(ActivityOnBoarding.this, ActivityHome.class);
                startActivity(intent);

            }
        });



    }

    public void addDotsIndicator(int position){
        mDots=new TextView[nDots];
        myLinearLayout.removeAllViews();

        for (int i=0;i<nDots;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.onboardingdotscolor));

            //ADDING DOTS TO LINEAR LAYOUT
            myLinearLayout.addView(mDots[i]);
        }

        if(position>=0) {
            mDots[position].setTextColor(getResources().getColor(R.color.onboardingdotshovercolor));
        }
    }

    ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}


