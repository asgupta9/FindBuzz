package com.example.findbuzz.findbuzz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by akash on 12/1/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{


    private Context context;
    private List<CardLayout> cardLayout;

//    public CustomAdapter(ActivityHome activityHome, List<CardLayout> data_list) {
//        this.context
//
//    }

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

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username,description;

        public ViewHolder(View itemView) {
            super(itemView);
            username=(TextView) itemView.findViewById(R.id.user_name);
            description=(TextView) itemView.findViewById(R.id.user_description);

        }
    }


}
