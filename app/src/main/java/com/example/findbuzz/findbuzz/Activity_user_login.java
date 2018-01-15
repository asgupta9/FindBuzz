package com.example.findbuzz.findbuzz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Activity_user_login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Log.d("Login Screen Check", "onCreateLoginScreenScreen: ");
        signInButton=(SignInButton)findViewById(R.id.google_sign_in_button);
        signInButton.setOnClickListener(this);
        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.google_sign_in_button:
                signIn();
                break;
            default:
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void signIn(){

        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }

    public void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient);
    }

    private void handleResult(GoogleSignInResult result){

        if(result.isSuccess()){
            //GOT USERNAME ,USER EMAIL,USER PROFILE PICTURE

            GoogleSignInAccount account= result.getSignInAccount();

            String name=account.getDisplayName();
            String email=account.getEmail();
            String img_url=account.getPhotoUrl().toString();

            Log.d("Debugging Google Auth", "handleResult: checking handleResult function");
            //Toast.makeText(this, "received"+name+email, Toast.LENGTH_LONG).show();

            //Make the shared preferences for the profile

            SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("username",name);
            editor.putString("useremail",email);
            editor.putString("userimgurl",img_url);
            editor.putString("isloggedin","1");
            editor.apply();

            Log.d("Activity_user_login", "handleResult: got user details from google Name: "+name+" Email: "+email+" Img_Url: "+img_url);
            signOut();

            String new_user_url = "http://home.iitj.ac.in/~sah.1/CFD2018/addUser.php";
            Log.d("Create New User", "handleResult: sending POST request to url: "+new_user_url+" for adding new user");
            String data = null;
            try {
                data = URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("name", "UTF-8") + "="
                        + URLEncoder.encode(name, "UTF-8");

                data += "&" + URLEncoder.encode("address", "UTF-8")
                        + "=" + URLEncoder.encode(" ", "UTF-8");

                data += "&" + URLEncoder.encode("phone", "UTF-8")
                        + "=" + URLEncoder.encode("1234", "UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.d("Check POST Data", "handleResult: generated data: "+data);
            new POST_data_on_server().execute(new_user_url, data);

            finish();
            // change the intent of the activity
            Intent intent=new Intent(Activity_user_login.this,ActivityHome.class);
            startActivity(intent);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE){
            //RECEIVE THE RESULTS
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);


        }

    }


}




