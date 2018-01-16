package com.example.findbuzz.findbuzz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by akash on 12/1/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{


    private Context context;
    private List<CardLayout> cardLayout;

    public CustomAdapter(Context context, List<CardLayout> cardLayout) {
        this.context = context;
        this.cardLayout = cardLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);

        return new ViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.username.setText(cardLayout.get(position).getUsername());
        holder.description.setText(cardLayout.get(position).getUserdescription());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        //  CUSTOM DIALOG

        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.lend_custom_dialog);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView title = (TextView) dialog.findViewById(R.id.custom_dialog_title);
//                TextView name = (TextView) dialog.findViewById(R.id.custom_dialog_username);
//                TextView date = (TextView) dialog.findViewById(R.id.custom_dialog_date);
        TextView description = (TextView) dialog.findViewById(R.id.custom_dialog_description);
        final EditText price = (EditText) dialog.findViewById(R.id.custom_dialog_price);
        final EditText remark = (EditText) dialog.findViewById(R.id.custom_dialog_remark);

        title.setText("Electronics");
//                name.setText(cardLayout.get(position).getUsername());
        description.setText(cardLayout.get(position).getUserdescription());
//                date.setText("12/1/2018");


        Button dialogButton = (Button) dialog.findViewById(R.id.xsubmit);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_request_url = "http://home.iitj.ac.in/~sah.1/CFD2018/addResponse.php";
                //String email = cardLayout.get(position).getUsername();
                SharedPreferences sharedPreferences=context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                final String name = sharedPreferences.getString("username","");
                final String email = sharedPreferences.getString("useremail","");

                String data = null;
                try {
                    data = URLEncoder.encode("lenderId", "UTF-8")
                            + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("price", "UTF-8")
                            + "=" + URLEncoder.encode(price.getText().toString(), "UTF-8");
                    data += "&" + URLEncoder.encode("remark", "UTF-8")
                            + "=" + URLEncoder.encode(remark.getText().toString(), "UTF-8");
                    data += "&" + URLEncoder.encode("requestId", "UTF-8")
                            + "=" + URLEncoder.encode(Integer.toString(cardLayout.get(position).getId()), "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("Check POST Data", "handleResult: generated data: "+data);
                new POST_data_on_server().execute(new_request_url, data);


                dialog.dismiss();

                //NEW ASYNTASK THREAD FOR UPDATING DATABASE
            }
        });

        dialog.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return cardLayout.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        public TextView username,description;
        private LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            username=(TextView) itemView.findViewById(R.id.user_name);
            description=(TextView) itemView.findViewById(R.id.user_description);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.card_view);
        }

        @Override
        public void onClick(View v) {

        }
    }






}
