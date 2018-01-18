package com.example.findbuzz.findbuzz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActivityRequestDetails extends AppCompatActivity {

    public ProgressDialog pDialog;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapterRequestResponse adapter;
    private List<RequestResponsesCardLayout> data_list;

    private static final String RESPONSE_URL = "http://home.iitj.ac.in/~sah.1/CFD2018/getResponse.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        Intent intent = getIntent();
        final String requestId = intent.getStringExtra("requestId");
        Log.d("RequestId of CardLayout", "Received request id:  "+requestId);

        //ASYNTASK THREAD
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_response);
        data_list= new ArrayList<>();
        Log.d("Before Network call: ", "onTabSelected: load data from server");
        load_data_from_server(0, requestId);
        Log.d("After Network call: ", "onTabSelected: merging recyclerview with adapter and layout manager");
        gridLayoutManager=new GridLayoutManager(ActivityRequestDetails.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new CustomAdapterRequestResponse(ActivityRequestDetails.this,data_list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition()==data_list.size()-1){
                    load_data_from_server(Integer.parseInt(data_list.get(data_list.size()-1).getId()), requestId);
                }
            }
        });

    }


    private void load_data_from_server(final int id, final String requestId) {
        AsyncTask<Integer,Void,Void> task=new AsyncTask<Integer,Void,Void>(){
            private String url;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(ActivityRequestDetails.this);
                pDialog.setMessage("Loading Items ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }
            @Override
            protected Void doInBackground(Integer... integers) {
                SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                final String email = sharedPreferences.getString("useremail","");
                final OkHttpClient client=new OkHttpClient();
                url=RESPONSE_URL+"?count="+id+"&requestId="+requestId;
                Log.d(" Doinbackgroundurl ",""+url);
                final Request request=new Request.Builder().url(url).build();
                try {
                    Response response=client.newCall(request).execute();
                    String jsonStr = response.body().string();
                    Log.d("JSON", "doInBackground: "+jsonStr);
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for (int i = 0; i< jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        RequestResponsesCardLayout cardLayout = new RequestResponsesCardLayout(object.getString("name"), object.getString("remark"), object.getString("date"), object.getString("price"), object.getString("responseId"), object.getString("lenderId"));
                        data_list.add(cardLayout);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                pDialog.hide();
                adapter.notifyDataSetChanged();
                Log.d(" Doinbackgroundjson","mkkknnkvgghjbh");
            }
        };
        task.execute(id);
    }

}
