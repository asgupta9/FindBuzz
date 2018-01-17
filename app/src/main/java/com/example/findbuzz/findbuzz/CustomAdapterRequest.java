package com.example.findbuzz.findbuzz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by akash on 15/1/18.
 */

public class CustomAdapterRequest extends RecyclerView.Adapter<CustomAdapterRequest.ViewHolder>{

    private Context context;
    private List<CardLayoutRequest> cardLayout;
    public CustomAdapterRequest(Context context, List<CardLayoutRequest> cardLayout) {
        this.context = context;
        this.cardLayout = cardLayout;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_request,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.requestDate.setText(cardLayout.get(position).getDate());
        holder.requestDescription.setText(cardLayout.get(position).getRequestDescription());
        holder.numberOfResponses.setText(Integer.toString(cardLayout.get(position).getNumberOfResponses()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempId=cardLayout.get(position).getId();
                Intent intent=new Intent(v.getContext(),ActivityRequestDetails.class);
                intent.putExtra("requestId",Integer.toString(tempId));
//                Log.d("RequestId of CardLayout", "Received request id:  "+Integer.toString(tempId));
                context.startActivity(intent);
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
        public TextView requestDate,requestDescription,numberOfResponses;
        private LinearLayout linearLayout;
        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            linearLayout=(LinearLayout)itemView.findViewById(R.id.card_view_request);
            requestDate=(TextView) itemView.findViewById(R.id.request_date);
            requestDescription=(TextView) itemView.findViewById(R.id.request_description);
            numberOfResponses=(TextView) itemView.findViewById(R.id.number_of_responses);


        }

        @Override
        public void onClick(View v) {
//            Log.d(TAG,"hello");
//            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
//            alertDialog.setTitle("hi");
//            alertDialog.setMessage("this is my app");
//
////            alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
////                public void onClick(DialogInterface dialog, int which) {
////                    // here you can add functions
////                }
////            });
//
//            Log.d(TAG,"hello");
//            alertDialog.show();  //<-- See This!
        }
    }
}
