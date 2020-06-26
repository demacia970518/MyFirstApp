package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;


public class WebViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }
    private Context context;
    private  WebViewData goodDetails;
    private JSONArray price;

    public WebViewAdapter(WebViewData goodDetails,Context context){
        this.context=context;
        this.goodDetails=goodDetails;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            View view = LayoutInflater.from(context).inflate(R.layout.goodimage, parent, false);
            ImageViewHolder linearViewHolder = new  ImageViewHolder(view);
            return linearViewHolder;
        } else if(viewType== ITEM_TYPE.ITEM_TYPE_TEXT.ordinal())  {
            View view = LayoutInflater.from(context).inflate(R.layout.gooddescription, parent, false);
            TextViewHolder linearViewHolder =new TextViewHolder(view);
            return linearViewHolder;
        }
       else {
            View view = LayoutInflater.from(context).inflate(R.layout.webview1, parent, false);
            WebViewHolder linearViewHolder =new WebViewHolder(view);
            return linearViewHolder;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() ;
        }
        else if(position==1) {
            return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
        }
        else{
            return  5;
        }
    }



    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ImageViewHolder){
            if(goodDetails.getBytes()!=null) {
                byte[] img = goodDetails.getBytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                ((ImageViewHolder) holder).imageName.setImageBitmap(bitmap);
            }
        }
        else if(holder instanceof TextViewHolder){
            ((TextViewHolder) holder).goodPrice.setText(goodDetails.getPrice());
            ((TextViewHolder) holder).goodDp.setText(goodDetails.getText());
        }
        else {

            ((WebViewHolder)holder).webView.getSettings().setJavaScriptEnabled(true);
            ((WebViewHolder)holder).webView.loadUrl("file:///android_asset/echarts.html");


            ((WebViewHolder)holder).webView.setWebViewClient(new WebViewClient(){
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if(Build.VERSION.SDK_INT>=19) {
                        ((WebViewHolder) holder).webView.evaluateJavascript("javascript:createCharts("+ goodDetails.getDate() +"," + goodDetails.getPrices() + ");", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                            }
                        });

                    }
                    else {
                        ((WebViewHolder) holder).webView.loadUrl("javascript:createCharts("+ goodDetails.getDate() +"," + goodDetails.getPrices() + ");");
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return 3;
    }


    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageName;


        public ImageViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageName=(ImageView)itemView.findViewById(R.id.good_image);

        }
    }
    class TextViewHolder extends RecyclerView.ViewHolder{
        TextView goodPrice;
        TextView goodDp;

        public TextViewHolder(@NonNull View itemView)
        {
            super(itemView);

            goodDp=(TextView)itemView.findViewById(R.id.good_dp);
            goodPrice=(TextView)itemView.findViewById(R.id.good_price);


        }
    }

    class WebViewHolder extends RecyclerView.ViewHolder{

        WebView webView;

        public WebViewHolder(@NonNull View itemView)
        {
            super(itemView);
            webView=(WebView)itemView.findViewById(R.id.view);

        }
    }

    public void data(){
        price=new JSONArray();
        price.put(1);
        price.put(2);
        price.put(3);
        price.put(4);
        price.put(5);
        price.put(6);



    }


}
