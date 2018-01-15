package com.example.findbuzz.findbuzz;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by akash on 15/1/18.
 */

public class POST_data_on_server extends AsyncTask<String, Void, Void> {
    public POST_data_on_server(){
        //set context variables if required
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        String data = params[1]; //data to post
        Log.d("CallAPI Async Task", "doInBackground: url: "+urlString+" params: "+data);
        OutputStream out = null;
        String text = "";
        BufferedReader reader=null;
        try {
            Log.d("Server Connection: ", "doInBackground: Starting try block ");
            URL url = new URL(urlString);
            Log.d("Server Connection: ", "doInBackground: extracted url from "+urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            //urlConnection.setRequestProperty("Content-Type", "text/plain");

            Log.d("Server Connection: ", "doInBackground: Opened connection ");

            out = new BufferedOutputStream(urlConnection.getOutputStream());
            Log.d("Server Connection: ", "doInBackground: New Output Stream ");

            BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(out, "UTF-8"));
            Log.d("Server Connection: ", "doInBackground: Initiated Writer ");

            writer.write(data);
            Log.d("Server Connection: ", "doInBackground: Written Data ");

            writer.flush();
            writer.close();
            Log.d("Server Connection: ", "doInBackground: Writer Closed ");

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Log.d("Server Connection: ", "doInBackground: Initiated Reader ");
            StringBuilder sb = new StringBuilder();
            Log.d("Server Connection: ", "doInBackground: Initiated New String Builder ");
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
            Log.d("Server Response: ", "doInBackground: "+text);
            out.close();
            urlConnection.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Log.d("WriterError", "doInBackground: Could Not Create New User");

        }
        return null;
    }
}