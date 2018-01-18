package com.example.findbuzz.findbuzz;

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

//    private String BASE_URL="";
//    @Override
//    public void onTokenRefresh() {
//        String token=FirebaseInstanceId.getInstance().getToken();
//        registerToken(token);
//    }
//
//
//    private void registerToken(String token){
//
//        OkHttpClient client=new OkHttpClient();
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("Token", token)
//                .build();
//
//        Request request = new Request.Builder()
//                .url(BASE_URL)
//                .post(requestBody)
//                .build();
//
//        try {
//            client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
