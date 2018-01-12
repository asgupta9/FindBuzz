package com.example.findbuzz.findbuzz;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


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
//    private RecyclerView recyclerView;

    // Creating JSON Parser object
//    JSONParser jsonParser = new JSONParser();


    private static final String INBOX_URL = " http://home.iitj.ac.in/~sah.1/CFD2018/getrqst.php ";

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
                    // fetch online cards
//                    recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                    recyclerView.setLayoutManager(mLayoutManager);
//
//                    new HttpRequestLendData().execute(INBOX_URL);
                    Toast.makeText(ActivityHome.this, "312", Toast.LENGTH_SHORT).show();
                }
                else{}
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    //FETCHING ONLINE CARDS ONLINE

    /*class HttpRequestLendData extends AsyncTask<String, Void, String> {
        private ProgressDialog pDialog;
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 20000;
        public static final int CONNECTION_TIMEOUT = 20000;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ActivityHome.this);
            pDialog.setMessage("Loading Inbox ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result="";
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }

//        protected void onPostExecute(String result){
//            super.onPostExecute(result);
//            pDialog.hide();
//
////            convert returned server response from json object to java object
//            JSONArray jsonArray;
//            ArrayList<card_template> modelArrayList=new ArrayList<>();
//            try {
//
//                jsonArray = new JSONArray(result);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    card_template model = new card_template();
//                    JSONObject temp = jsonArray.getJSONObject(i);
//                    model.request_id = temp.getString("rqst_id");
//                    model.borrower_id = temp.getString("borrower_id");
//                    model._description = temp.getString("description");
//                    model.request_time = temp.getString("time");
//
//                    Log.d("JSON", "onPostExecute: "+model.request_id);
//                    modelArrayList.add(model);
//
//
//
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }

    }

*/

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
