package com.example.myfirstapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetLIstData {
    private static final String TAG ="size" ;
    Goods goods;

    List<Goods> goodList;


    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String str = (String) msg.obj;
                    try {
                        JSONArray jsonArray=new JSONArray(str);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String id=jsonObject.getString("id");
                            String name=jsonObject.getString("name");
                           // goods =new Goods(id,name,R.drawable.saina);
                            goodList.add(goods);
                            String st =String.valueOf(goodList.size());
                            Log.d(TAG,"size"+st);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
            return true;
        }
    });

    public List<Goods> getGoodList(){
        return  goodList;

    }





    public void getOk(){
        OkHttpClient okHttpClient =new OkHttpClient();
        String url="http://guolin.tech/api/china";
        Request request =new Request.Builder().url(url).build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Message message =Message.obtain();
                if(response.isSuccessful()){
                    message.what=1;
                    message.obj=response.body().toString();
                    handler.sendMessage(message);

                }
                else {
                    handler.sendEmptyMessage(0);
                }

            }
        });

    }
}
