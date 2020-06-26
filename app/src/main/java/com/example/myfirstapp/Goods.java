package com.example.myfirstapp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {

    private String text;
    private String price;
    private String comment;
    private String imgUrl;
    //private List<JDPirce> dateprice;
    private  int image;
    private long sku;
    private Bitmap bitmap;
    private JDPirce jdPirce;
    private  byte[] imgbytes;


    public Goods(String text,String price,String comment,long sku,byte[] imgbytes){
        this.text=text;
        this.price=price;
        this.comment=comment;
        this.sku=sku;
        this.imgbytes=imgbytes;


    }


    public  Goods(String text,String price,int image,byte[] imgbytes){
        this.text=text;
        this.price=price;
        this.image=image;
        this.imgbytes=imgbytes;
    }
    public  Goods(String text,String price,String url){
        this.text=text;
        this.price=price;
        this.image=image;
        this.imgUrl=url;

    }



    public  Goods(String text,String price,int image){
        this.text=text;
        this.price=price;
        this.image=image;

    }

    public Goods(String text, String price, String comment, String img) {
        this.text=text;
        this.price=price;
        this.comment=comment;
        this.imgUrl=img;
    }
    //public JDPirce getJdPircejdPirce()

    public String getText(){
        return text;
    }

    public int getImage(){
        return  image;
    }

    public String getPrice(){
        return  price;
    }
    public String getImgUrl(){
        return  imgUrl;
    }
    public Bitmap getBitmap(){
        return  bitmap;
    }

    public byte[] getImgBytes(){
        return  imgbytes;
    }
    public long getSku() {
        return sku;
    }
    public String getComment(){
        return  comment;
    }



}
