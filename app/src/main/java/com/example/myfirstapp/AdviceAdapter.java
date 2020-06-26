package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import okhttp3.OkHttpClient;

public class AdviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private  Context context;
    private List<Goods> goods;
    private List<SuggGoods> advices;
    public static final int GIRD_VIEW = 0;
    public static final int LIST_VIEW = 1;

    private MyOnClickListener MyOnClickListener;

    public AdviceAdapter(List<Goods> goods,List<SuggGoods> advices, Context context){
        this.context=context;
        this.goods=goods;
        this.advices=advices;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LIST_VIEW) {
            return new LinearViewHolder(LayoutInflater.from(context).inflate(R.layout.goods, parent, false));
        }
        else {
            return new GridViewHolder(LayoutInflater.from(context).inflate(R.layout.gridview, parent, false));
        }
    }



    @Override
    public int getItemViewType(int position) {
        if (position==0)
        return GIRD_VIEW;
        else {
            return  LIST_VIEW;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LinearViewHolder) {
            ((LinearViewHolder) holder).imageName.setImageResource(goods.get(position).getImage());
            ((LinearViewHolder) holder).text.setText(goods.get(position).getText());
            ((LinearViewHolder) holder).price.setText(goods.get(position).getPrice());
            ((LinearViewHolder) holder).cardView.setTag(position);
            ((LinearViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyOnClickListener.onClick(v, (int) v.getTag(),0);
                }
            });

        }
        else {

            ((GridViewHolder)holder).recyclerView.setLayoutManager(new GridLayoutManager(context,4));
            ((GridViewHolder)holder).recyclerView.setTag(position);
            GridAdapter gridAdapter =new GridAdapter(advices);
            gridAdapter.setMyOnClickListener(new MyOnClickListener() {
                @Override
                public void onClick(View view, int position, int child_position) {
                    MyOnClickListener.onClick(view, 0, position);
                }
            });
            ((GridViewHolder)holder).recyclerView.setAdapter(gridAdapter);

        }

    }

    @Override
    public int getItemCount() {

        return goods.size();
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.MyOnClickListener = myOnClickListener;
    }
    class LinearViewHolder extends RecyclerView.ViewHolder{
        ImageView imageName;
        TextView  text;
        TextView price;
        CardView cardView;
        public LinearViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageName=(ImageView)itemView.findViewById(R.id.good_imag);
            text=(TextView)itemView.findViewById(R.id.good_text);
            price=(TextView)itemView.findViewById(R.id.good_price1);
            cardView=itemView.findViewById(R.id.card_good);

        }
    }
    class GridViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public GridViewHolder(@NonNull View itemView)
        {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.gridView);

        }
    }
    class SuggestViewHolder extends RecyclerView.ViewHolder{

        public SuggestViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
