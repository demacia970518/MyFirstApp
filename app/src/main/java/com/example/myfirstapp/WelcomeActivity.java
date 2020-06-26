package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends Activity {

   private Handler handler;
   private Runnable runnable;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        handler=new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(WelcomeActivity.this,Advices.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable,2000);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);

    }
}
