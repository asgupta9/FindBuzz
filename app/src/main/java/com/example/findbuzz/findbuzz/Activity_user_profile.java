package com.example.findbuzz.findbuzz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class Activity_user_profile extends AppCompatActivity {


    private Button btn;
    private TextView username,useremail;
    private ImageView imgview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String name= sharedPreferences.getString("username","");
        String email=sharedPreferences.getString("useremail","");
        String img_url=sharedPreferences.getString("userimgurl","");

        username=(TextView) findViewById(R.id.user_name);
        useremail=(TextView) findViewById(R.id.user_email);
        imgview=(ImageView) findViewById(R.id.user_profile_image);
        btn=(Button) findViewById(R.id.logout_button);
        String[] strArray = name.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
        username.setText(builder.toString());
        useremail.setText(email);


        Log.d("TAG",img_url);

        //Glide.with(this).load(img_url).into(imgview);
        Glide.with(this).load(img_url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgview) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgview.setImageDrawable(circularBitmapDrawable);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //logout codes


                SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username","");
                editor.putString("useremail","");
                editor.putString("userimgurl","");
                editor.putString("isloggedin","0");
                editor.apply();

                //logged out
                finish();
                Intent intent=new Intent(Activity_user_profile.this,Activity_user_login.class);
                startActivity(intent);

            }
        });


    }

}
