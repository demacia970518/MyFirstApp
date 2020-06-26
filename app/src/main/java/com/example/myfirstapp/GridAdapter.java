package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {


    private List<SuggGoods> advices;
    private MyOnClickListener MyOnClickListener;

    public GridAdapter(List<SuggGoods> advices){

        this.advices=advices;
    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion,parent,false);
        GridViewHolder gridViewHolder=new GridViewHolder(view);
       // view.setOnClickListener(this);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.GridViewHolder holder, int position) {
        holder.imageView.setImageResource(advices.get(position).getImage());
        holder.textView.setText(advices.get(position).getItem());
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOnClickListener.onClick(v,(int)v.getTag(),0);
            }
    });



    }

    @Override
    public int getItemCount() {
        return advices.size();
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.MyOnClickListener = myOnClickListener;
    }

  /*  @Override
    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onClick(v,(int)v.getTag());
        }
    }*/

    class GridViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView cardView;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.card_advice);
            textView=(TextView)itemView.findViewById(R.id.sug_text);
            imageView=(ImageView)itemView.findViewById(R.id.sug_img);

        }
    }


}
