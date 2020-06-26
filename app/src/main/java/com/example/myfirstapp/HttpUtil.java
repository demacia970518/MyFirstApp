package com.example.myfirstapp;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

    private OkHttpClient okHttpClient;

    public HttpUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }


    public void sendOkHttpRequest(String address, okhttp3.Callback callback) {


        Request request = new Request.Builder()
                .url(address).build();

        okHttpClient.newCall(request).enqueue(callback);
    }

    public Call sendOkHttp(String address) {


        Request request = new Request.Builder()
                .url(address).build();

        Call call = okHttpClient.newCall(request);

        return call;
    }
}
