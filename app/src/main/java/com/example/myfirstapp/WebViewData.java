package com.example.myfirstapp;

import android.webkit.WebView;

import org.json.JSONArray;

import java.io.Serializable;

public class WebViewData implements Serializable {
    private String text;
    private String price;
    private  int image;
    private WebView webView;
    private byte[] bytes;
    private JSONArray date;
    private  JSONArray prices;


    public WebViewData(String text, String price, byte[] bytes,JSONArray date,JSONArray prices, WebView webView){
        this.text=text;
        this.price=price;
        this.date=date;
        this.prices =prices;
        this.bytes=bytes;
        this.webView=webView;
    }

    public String getText(){
        return text;
    }

    public int getImage(){
        return  image;
    }

    public String getPrice(){
        return  price;
    }

    public byte[]  getBytes(){
        return  bytes;
    }

    public JSONArray getDate() {
        return date;
    }

    public JSONArray getPrices() {
        return prices;
    }

    public WebView getWebView(){
        return webView;
    }


}
