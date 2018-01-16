package com.example.findbuzz.findbuzz;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ActivityHome extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public ProgressDialog pDialog;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TabLayout tabLayout;

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewRequests;

    private GridLayoutManager gridLayoutManager;

    private CustomAdapter adapter;
    private CustomAdapterRequest adapterRequest;

    private List<CardLayout> data_list;
    private List<CardLayoutRequest> data_list_requests;

//    private RecyclerView recyclerView;

    // Creating JSON Parser object
//    JSONParser jsonParser = new JSONParser();


    private static final String ACCORD_URL = "http://home.iitj.ac.in/~sah.1/CFD2018/getRequest.php";
    private static final String BORROW_URL = "http://home.iitj.ac.in/~sah.1/CFD2018/getMyRequests.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){

        });
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
//
//        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                if(position==1){

                    Log.d("Selected Teb 1: ", "onTabSelected: proceed to create recyclerviewcards");
                    recyclerView=(RecyclerView)findViewById(R.id.recycler_view_lend_action_area);
                    data_list= new ArrayList<>();
//                    Toast.makeText(ActivityHome.this, "312", Toast.LENGTH_SHORT).show()
                    Log.d("Before Network call: ", "onTabSelected: load data from server");
                    load_data_from_server(0);
                    Log.d("After Network call: ", "onTabSelected: merging recyclerview with adapter and layout manager");
                    gridLayoutManager=new GridLayoutManager(ActivityHome.this,1);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter=new CustomAdapter(ActivityHome.this,data_list);
                    recyclerView.setAdapter(adapter);

                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            if(gridLayoutManager.findLastCompletelyVisibleItemPosition()==data_list.size()-1){
                                load_data_from_server(data_list.get(data_list.size()-1).getId());
                            }
                        }
                    });

                }
                else{
                    //find action area

                    recyclerViewRequests=(RecyclerView)findViewById(R.id.recycler_view_search_action_area);
                    data_list_requests= new ArrayList<>();
                    load_data_from_server_request(0);
                    gridLayoutManager=new GridLayoutManager(ActivityHome.this,1);
                    recyclerViewRequests.setLayoutManager(gridLayoutManager);
                    adapterRequest=new CustomAdapterRequest(ActivityHome.this,data_list_requests);
                    recyclerViewRequests.setAdapter(adapterRequest);
                    recyclerViewRequests.addOnScrollListener(new RecyclerView.OnScrollListener() {


                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            if(gridLayoutManager.findLastCompletelyVisibleItemPosition()==data_list_requests.size()-1){
                                load_data_from_server_request(data_list_requests.get(data_list_requests.size()-1).getId());
                            }
                        }
                    });



                }
            }

            private void load_data_from_server_request(final int id) {
                AsyncTask<Integer,Void,Void> task=new AsyncTask<Integer,Void,Void>(){
                    private String url;
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        pDialog = new ProgressDialog(ActivityHome.this);
                        pDialog.setMessage("searching your requests :)");
                        pDialog.setIndeterminate(false);
                        pDialog.setCancelable(true);
                        pDialog.show();
                    }
                    @Override
                    protected Void doInBackground(Integer... integers) {
                        SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                        final String email = sharedPreferences.getString("useremail","");
                        final OkHttpClient client=new OkHttpClient();
                        url=BORROW_URL+"?id="+id+"&emailid="+email;
                        Log.d(" Doinbackgroundurl ",""+url);
                        final Request request=new Request.Builder().url(url).build();
                        try {
                            Response response=client.newCall(request).execute();
                            String jsonStr = response.body().string();
                            JSONArray jsonArray = new JSONArray(jsonStr);
                            for (int i = 0; i< jsonArray.length(); i++){
                                JSONObject object= jsonArray.getJSONObject(i);
                                CardLayoutRequest cardLayout=new CardLayoutRequest(object.getInt("requestId"),object.getString("requestDate"),object.getString("description"));
                                data_list_requests.add(cardLayout);
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
                        adapterRequest.notifyDataSetChanged();
                        Log.d(" Doinbackgroundjson","mkkknnkvgghjbh");
                    }
                };
                task.execute(id);
            }


            private void load_data_from_server(final int id) {
              AsyncTask<Integer,Void,Void> task=new AsyncTask<Integer,Void,Void>(){
                  private String url;
                  @Override
                  protected void onPreExecute() {
                      super.onPreExecute();
                      pDialog = new ProgressDialog(ActivityHome.this);
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
                        url=ACCORD_URL+"?id="+id+"&emailid="+email;
                        Log.d(" Doinbackgroundurl ",""+url);
                        final Request request=new Request.Builder().url(url).build();
                        try {
                            Response response=client.newCall(request).execute();
                            String jsonStr = response.body().string();
                            JSONArray jsonArray = new JSONArray(jsonStr);
                            for (int i = 0; i< jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                CardLayout cardLayout = new CardLayout(object.getInt("requestId"), object.getString("borrowerId"), object.getString("description"));
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



            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }



    public void searchActivity(View view){
        //calling new search box activity
        Intent i=new Intent(ActivityHome.this,ActivityFillSearchDetails.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.action_profile){
            Intent intent=new Intent(ActivityHome.this,Activity_user_profile.class);
            startActivity(intent);
        }
        if(id==R.id.action_logout){

        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    SearchActionHome searchactionhome=new SearchActionHome();
                    return searchactionhome;

                case 1:
                    LendActionHome lendactionhome=new LendActionHome();
                    return lendactionhome;


                default:
                    return null;

            }
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
