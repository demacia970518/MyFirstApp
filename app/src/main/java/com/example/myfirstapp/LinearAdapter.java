package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {

    private  Context context;
    private List<Goods> goods;
    private List<byte[]> imglist;
    private  Bitmap bitmap;
    private MyOnClickListener MyOnClickListener;
    /*private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    byte[] bytes = (byte[]) msg.obj;
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            }
            return true;
        } });*/



    public LinearAdapter(List<Goods> goods,List<byte[]> imglist,Context context){
        this.context=context;
        this.goods=goods;
        this.imglist =imglist;

    }

    public LinearAdapter(List<Goods> goods,Context context){
        this.context=context;
        this.goods=goods;


    }



    @NonNull
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(context).inflate(R.layout.goods,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.LinearViewHolder holder, int position) {
       // Glide.with(context).load(goods.get(position).getImgUrl()).into(holder.imageName);
      //  if(imglist.get(position)!=null){
           // byte[] img =imglist.get(position);

           // Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);

            //holder.imageName.setImageBitmap(bitmap);
        //}
       // else {
            //Log.d("tumainne","wotupianne");
       // }
      /*  final String url =goods.get(position).getImgUrl();
        new Thread(new Runnable() {
            @Override
            public void run() {
           MyApplication.myClient.sendOkHttp(url);
            }
        });*/

        //Glide.with(context).load(goods.get(position).getImgUrl()).into(holder.imageName);
        if(goods.get(position).getImgBytes()!=null) {
            byte[] img = goods.get(position).getImgBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.imageName.setImageBitmap(bitmap);
        }
        else {
            holder.imageName.setImageResource(goods.get(position).getImage());
        }


    holder.comment.setText(goods.get(position).getComment());
    holder.text.setText(goods.get(position).getText());
    holder.price.setText(goods.get(position).getPrice());
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

        return goods.size();
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.MyOnClickListener = myOnClickListener;
    }
    class LinearViewHolder extends RecyclerView.ViewHolder{
        ImageView imageName;
        TextView  text;
        TextView price;
        TextView comment;
        CardView cardView;
        public LinearViewHolder(@NonNull View itemView)
        {
            super(itemView);
            comment=(TextView)itemView.findViewById(R.id.good_comment);
            imageName=(ImageView)itemView.findViewById(R.id.good_imag);
            text=(TextView)itemView.findViewById(R.id.good_text);
            price=(TextView)itemView.findViewById(R.id.good_price1);
            cardView=itemView.findViewById(R.id.card_good);

        }
    }

}
