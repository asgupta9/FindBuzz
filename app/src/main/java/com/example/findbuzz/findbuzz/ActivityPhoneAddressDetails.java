package com.example.findbuzz.findbuzz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ActivityPhoneAddressDetails extends AppCompatActivity {


    private EditText phoneNumber;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_address_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        phoneNumber=(EditText)findViewById(R.id.user_phone_details);
        submit=(Button) findViewById(R.id.xsubmit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here to update phone number in user database
                String pNumber = phoneNumber.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                final String name = sharedPreferences.getString("username","");
                final String email = sharedPreferences.getString("useremail","");

                if(pNumber != null && !pNumber.isEmpty()){
                    Log.d("Mobile", "run: "+pNumber);
//                    submit phone number to server
                    String updateUserDetails = "http://home.iitj.ac.in/~sah.1/CFD2018/addUser.php";

                    Log.d("Create New User", "handleResult: sending POST request to url: "+updateUserDetails+" for adding new user");
                    String data = null;
                    try {
                        data = URLEncoder.encode("email", "UTF-8")
                                + "=" + URLEncoder.encode(email, "UTF-8");
                        data += "&" + URLEncoder.encode("phone", "UTF-8") + "="
                                + URLEncoder.encode(pNumber, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.d("Check POST Data", "handleResult: generated data: "+data);
                    new POST_data_on_server().execute(updateUserDetails, data);


                    finish();
                    Intent intent = new Intent(ActivityPhoneAddressDetails.this, ActivityHome.class);
                    startActivity(intent);
                }
                else{

                }

            }
        });





    }

}
