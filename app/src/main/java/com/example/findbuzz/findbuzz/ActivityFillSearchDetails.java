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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ActivityFillSearchDetails extends AppCompatActivity {

    private Button btn;
    private EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_search_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner;
        spinner=(Spinner) findViewById(R.id.categories);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.category_arrays, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinner.setAdapter(adapter);

        SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("username","");
        final String email = sharedPreferences.getString("useremail","");

        btn=(Button) findViewById(R.id.submit);
        description = (EditText) findViewById(R.id.description);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //Code to post search query on server

                String new_request_url = "http://home.iitj.ac.in/~sah.1/CFD2018/addRequest.php";
                Log.d("Create New User", "handleResult: sending POST request to url: "+new_request_url+" for adding new Request");
                String data = null;
                try {
                    data = URLEncoder.encode("borrowerid", "UTF-8")
                            + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("description", "UTF-8")
                            + "=" + URLEncoder.encode(description.getText().toString(), "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("Check POST Data", "handleResult: generated data: "+data);
                new POST_data_on_server().execute(new_request_url, data);


                finish();
                Intent intent=new Intent(ActivityFillSearchDetails.this,ActivityHome.class);
                startActivity(intent);

            }
        });

    }
}
