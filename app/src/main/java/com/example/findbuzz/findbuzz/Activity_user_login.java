package com.example.findbuzz.findbuzz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;

public class Activity_user_login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

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

            //Toast.makeText(this, "received"+name+email, Toast.LENGTH_LONG).show();

            //Make the shared preferences for the profile

            SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("username",name);
            editor.putString("useremail",email);
            editor.putString("userimgurl",img_url);
            editor.putString("isloggedin","1");
            editor.apply();

            signOut();

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




