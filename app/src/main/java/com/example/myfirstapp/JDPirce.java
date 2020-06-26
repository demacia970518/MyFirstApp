package com.example.myfirstapp;

import android.os.Parcel;
import android.os.Parcelable;


import java.io.Serializable;
import java.util.Date;

public class JDPirce implements Serializable {
     private String date;
     private double price;


     public  JDPirce(String date,double price){
         this.date=date;
         this.price=price;

     }



    public String getDate(){
         return date;
     }

     public double getPrice(){
         return  price;
     }



    @Override
    public String toString() {
        return "JDPirce{" +
                "date=" + date +
                ", price=" + price +
                '}';
    }



}
