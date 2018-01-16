package com.example.findbuzz.findbuzz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by akash on 16/1/18.
 */

public class CustomAdapterRequestResponse extends  RecyclerView.Adapter<CustomAdapterRequestResponse.ViewHolder>{
    private Context context;
    private List<RequestResponsesCardLayout> cardLayout;
    public CustomAdapterRequestResponse(Context context, List<RequestResponsesCardLayout> cardLayout) {
        this.context = context;
        this.cardLayout = cardLayout;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.request_response_details,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.responseUsername.setText(cardLayout.get(position).getUsername());
        holder.responseDate.setText(cardLayout.get(position).getDate());
        holder.remarkDescription.setText(cardLayout.get(position).getRemark());
        holder.responsePrice.setText(cardLayout.get(position).getPrice());


        holder.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CALL ACTIVITY USING RESPONSER'S PHONE NUMBER

            }
        });

        holder.buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MESSAGE ACTIVITY USING RESPONSER'S PHONE NUMBER

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
        public TextView responseUsername,remarkDescription,responsePrice,responseDate;
        private Button buttonCall,buttonMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            responseUsername=(TextView) itemView.findViewById(R.id.response_user_name);
            remarkDescription=(TextView) itemView.findViewById(R.id.remark_description);
            responsePrice=(TextView) itemView.findViewById(R.id.response_price);
            responseDate=(TextView) itemView.findViewById(R.id.response_date);
            buttonCall=(Button) itemView.findViewById(R.id.button_call);
            buttonMessage=(Button) itemView.findViewById(R.id.button_message);


        }

        @Override
        public void onClick(View v) {

        }
    }
}
