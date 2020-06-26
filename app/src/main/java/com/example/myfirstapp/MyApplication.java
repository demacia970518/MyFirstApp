package com.example.myfirstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    //public static MyHttp myClient;
    private String text=null;
    public static HttpUtil  myClient;
    private  AlertDialog.Builder builder;
    private static final String TAG = "Right";
    @Override
    public void onCreate() {
        super.onCreate();

        myClient=new HttpUtil();
        //myClient =new MyHttp();
        initBackgroundCallBack();
    }

    private void initBackgroundCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            private  int count;
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                if (activity.getLocalClassName().equals("WelcomeActivity")) {

                } else {
                    Log.d(TAG, "count:" + count);
                    count++;

                    Log.d(TAG, "onActivityStarted: " + activity.getLocalClassName());
                    Log.d(TAG, "count:" + count);
                    if (count == 1) {
                        text = " ";
                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        if(clipboardManager.getPrimaryClip()!=null) {
                            ClipData data = clipboardManager.getPrimaryClip();

                            Log.d(TAG, "text:" + data.getItemAt(0).getText());
                            if ((data.getItemAt(0).getText())!=null) {

                                text = data.getItemAt(0).getText().toString();
                            }
                        }

                        if (text != null) {

                           isUrl(activity);
                           // getDialog(activity);
                            Log.d(TAG, "text:" + text);

                            ClipData clip = ClipData.newPlainText("", "");
                            clipboardManager.setPrimaryClip(clip);
                        }
                    }

                }
            }



            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.d(TAG, "onActivityResumed: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.d(TAG, "onActivityPaused: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                if (activity.getLocalClassName().equals("WelcomeActivity")) {

                } else {
                    Log.d(TAG, "count:" + count);
                    count--;
                    Log.d(TAG, "onActivityStopped: " + activity.getLocalClassName());
                    Log.d(TAG, "count:" + count);
                }
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.d(TAG,"onActivityDestroyed: " + activity.getLocalClassName());
            }
        });
    }

    private void isUrl(@NonNull Activity activity) {
         Pattern httpPattern;

        httpPattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");

        if (httpPattern.matcher(text).matches()) {
            getDialog(activity);
        }

    }

    public  String getText(){
        return text;
    }

    public void setText(String text){
        this.text=text;
    }


    private  void getDialog(@NonNull Activity activity){
        builder =new AlertDialog.Builder(activity);
        builder.setTitle("你复制了一个网址");
        builder.setMessage("要打开剪贴板中的网址吗？"+text);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
            /*    Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(text));
                startActivity(intent);*/
                Log.d(TAG,"text:queding");
            }
        });
        builder.setNegativeButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG,"text:buqued");
            }
        });
        builder.create().show();

    }



}
