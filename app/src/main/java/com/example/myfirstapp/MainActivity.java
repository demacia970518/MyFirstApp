package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import static com.example.myfirstapp.R.*;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="com.example.myfirstapp.MESSAGE";
    private ClipboardManager clipboard;
    private  AlertDialog.Builder builder;
    private static final String TAG = "aa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
    /*    clipboard=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                getText();
                System.out.println("onPrimaryClipChanged()");
            }
        });**/

    }

    public void SendMessage(View view){
        Intent intent = new Intent(this,demcaia.class);
        startActivity(intent);
    }

    public void SendMessage2(View view){
        Intent intent = new Intent(this,listview.class);
        startActivity(intent);
    }

    public void SendMessage1(View view){
        Intent intent = new Intent(this,OpenUrl.class);
        startActivity(intent);
    }
    public void SendMessage4(View view){
        Intent intent = new Intent(this,Advices.class);
        startActivity(intent);
    }

    public void Dialog(View view){
        if(builder!=null){
            AlertDialog ad =builder.show();
            ad.dismiss();
        }
        builder =new AlertDialog.Builder(this);
        builder.setTitle("你复制了一个网址");
        builder.setMessage("要打开剪贴板中的网址吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG,"text:queding");

            }
        });
        builder.setNegativeButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG,"text:queding");
            }
        });
        builder.create().show();
    }

    public void getText(){
        String text="";
        if(clipboard.hasPrimaryClip()){
            CharSequence item =clipboard.getPrimaryClip().getItemAt(0).getText();
            text= String.valueOf(item);

        }
    }



}