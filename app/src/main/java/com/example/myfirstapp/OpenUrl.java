package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenUrl extends AppCompatActivity {
    Goods goods;

    List<Goods> goodList;
   Button button;
   TextView textView;
   ImageView imageView;
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                  byte[] bytes =(byte[])msg.obj;
                  Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                  imageView.setImageBitmap(bitmap);
            }
            return true;
        }
    });
    private static final String TAG ="why" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_url);
        button=findViewById(R.id.button6);
        textView=findViewById(R.id.testok);
        imageView=findViewById(R.id.imageView7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOk();
                Log.d(TAG,"text");
            }
        });
    }


    public void getImage(){
        new Thread(new Runnable() {
           Drawable drawable;
            @Override
            public void run() {

            }
        });

    }

    public void getOk(){
        OkHttpClient okHttpClient =new OkHttpClient();
        String url="http://10.20.200.48:8080/web/gen/getImg?pt=jd&sku=1111";
        Request request =new Request.Builder().url(url).build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"textfa");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Message message =Message.obtain();
                if(response.isSuccessful()){
                    Log.d(TAG,"text1");
                    byte[] bytes =response.body().bytes();

                    message.what=1;
                    message.obj=bytes;
                    handler.sendMessage(message);


                }
                else {
                    Log.d(TAG,"textf");
                    handler.sendEmptyMessage(0);
                }

            }
        });

    }


}