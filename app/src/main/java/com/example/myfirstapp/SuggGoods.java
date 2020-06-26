package com.example.myfirstapp;

import java.io.Serializable;

public class SuggGoods implements Serializable {
    private int image;
    private  String item;

    public SuggGoods(int image,String text){
        this.image=image;
        this.item=text;
    }

    public int getImage() {
        return image;
    }

    public String getItem(){
        return item;
    }
}
