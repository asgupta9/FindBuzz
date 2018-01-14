package com.example.findbuzz.findbuzz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by akash on 12/1/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{



//    public static class ViewHolder extends RecyclerView.ViewHolder
//
//        implements View.OnClickListener{
//        private String mItem;
//        private TextView mTextView;
//
//        public ViewHolder(View view) {
//            super(view);
//            view.setOnClickListener(this);
//            mTextView = (TextView) view;
//        }
//
//        public void setItem(String item) {
//            mItem = item;
//            mTextView.setText(item);
//        }
//
//        @Override
//        public void onClick(View view) {
//            Log.d(TAG, "onClick " + getPosition() + " " + mItem);
//        }
//
//
//
//    }



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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.username.setText(cardLayout.get(position).getUsername());
        holder.description.setText(cardLayout.get(position).getUserdescription());
    }

    @Override
    public int getItemCount() {
        return cardLayout.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        public TextView username,description;

        public ViewHolder(View itemView) {
            super(itemView);
            username=(TextView) itemView.findViewById(R.id.user_name);
            description=(TextView) itemView.findViewById(R.id.user_description);
        }

        @Override
        public void onClick(View v) {


            Log.d(TAG,"hello");

            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
            alertDialog.setTitle("hi");
            alertDialog.setMessage("this is my app");

//            alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // here you can add functions
//                }
//            });

            Log.d(TAG,"hello");
            alertDialog.show();  //<-- See This!



        }
    }






}
