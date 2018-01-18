package com.example.findbuzz.findbuzz;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by akash on 18/1/18.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    //private String BASE_URL="http://home.iitj.ac.in/~sah.1/CFD2018/addUser.php";
    @Override
    public void onTokenRefresh() {



        String token=FirebaseInstanceId.getInstance().getToken();
        saveTokenToPrefs(token);

        //registerToken(token);


    }
    public void registerToken(String token){

        String email=getTokenFromPrefs();
        String BASE_URL="http://home.iitj.ac.in/~sah.1/CFD2018/addUser.php";

        Log.d("Token","this is your token"+email+" or "+ token);

        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token",token)
                .addFormDataPart("email",email)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(requestBody)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void saveTokenToPrefs(String _token){
        // Access Shared Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString("token", _token);
        editor.apply();
    }

    private String getTokenFromPrefs()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("token", null);
    }



}
