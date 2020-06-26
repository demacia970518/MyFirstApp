package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class webView extends AppCompatActivity {
    private WebView webView;
    private JSONArray price;
    private JSONArray date;
    private  Goods goods;

    private long sku;
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd");
    private BottomNavigationView bottom;
    public static final  String TAG="webView";
    private RecyclerView recyclerView;
    private WebViewData goodDetails;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        //String sku =getIntent().getStringExtra("sku");
        //show();
      //  data();

       // MyApplication.myClient.get("http://hatlate.cn/web/search",UrlParam.addParam("key", sku));





        JSONArray price = new JSONArray();
        JSONArray dates = new JSONArray();
        goods= (Goods) getIntent().getSerializableExtra("good");
        List<JDPirce> list = (List<JDPirce>) getIntent().getSerializableExtra("date");
        Collections.sort(list, new Comparator<JDPirce>() {
            @Override
            public int compare(JDPirce o1, JDPirce o2) {
                try {
                    if(format.parse(o1.getDate()).after(format.parse(o1.getDate()))){
                        return 1;
                    }
                    else {
                        return -1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                    return 0;
            }


        });
        for (int j=0; j<list.size();j++){
            Log.d("prices",list.get(j).toString());


            try {

                price.put(list.get(j).getPrice());
                dates.put(list.get(j).getDate());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycle_web);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        goodDetails =new WebViewData(goods.getText(),goods.getPrice(),goods.getImgBytes(),dates,price,webView);
        recyclerView.setAdapter(new WebViewAdapter(goodDetails, webView.this));


     /*   MyApplication.myClient.sendOkHttpRequest("http://10.20.200.48:8080/web/get/GetItems?key="+sku, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Message message =Message.obtain();
                String str =response.body().string();
                message.what=1;
                message.obj=str;
                handler.sendMessage(message);
            }
        });*/








        /*  webView = (WebView) findViewById(R.id.view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/echarts.html");
        data();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i(TAG,"onPageFinished");
                message();
            }
        });*/

       // goods = (Goods) getIntent().getSerializableExtra("stu");



    }

    public void data(){



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void message(JSONArray date ,JSONArray price) {
        if(Build.VERSION.SDK_INT>=19) {
            webView.evaluateJavascript("javascript:createCharts("+ date +"," + price + ");", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                   Log.i(TAG,"print"+value);
                }
            });

            }
        else {
            webView.loadUrl("javascript:createCharts("+ date +"," + price + ");");
        }
    }




    public void jumpToOhter(View view){


        Intent intent=new Intent(Intent.ACTION_VIEW);
        //String url ="https://item.jd.com/26594752595.html";
        //String url="https://item.m.jd.com/product/4264880.html";
        long sku =goods.getSku();
        String url ="openApp.jdMobile://virtual?params=%7B%22category%22%3A%22jump%22%2C%22des%22%3A%22productDetail%22%2C%22skuId%22%3A%22"+sku+"%22%2C%22sourceType%22%3A%22Item%22%2C%22sourceValue%22%3A%22view-ware%22%2C%22M_sourceFrom%22%3A%22sx%22%2C%22msf_type%22%3A%22adshare%22%2C%22m_param%22%3A%7B%22m_source%22%3A%220%22%2C%22event_series%22%3A%7B%7D%2C%22jda%22%3A%22122270672.1592378833367223401773.1592378833.1592378833.1592378833.1%22%2C%22usc%22%3A%22androidapp%22%2C%22ucp%22%3A%22t_335139774%22%2C%22umd%22%3A%22appshare%22%2C%22utr%22%3A%22CopyURL%22%2C%22jdv%22%3A%22122270672%7Candroidapp%7Ct_335139774%7Cappshare%7CCopyURL%7C1592378834064%22%2C%22ref%22%3A%22https%3A%2F%2Fitem.m.jd.com%2Fproduct%2F4264880.html%3Fwxa_abtest%3Do%26ad_od%3Dshare%26utm_source%3Dandroidapp%26utm_medium%3Dappshare%26utm_campaign%3Dt_335139774%26utm_term%3DCopyURL%22%2C%22psn%22%3A%221592378833367223401773%7C1%22%2C%22psq%22%3A1%2C%22unpl%22%3A%22%22%2C%22pc_source%22%3A%22%22%2C%22mba_muid%22%3A%221592378833367223401773%22%2C%22mba_sid%22%3A%2215923788333767910425099421652%22%2C%22std%22%3A%22MO-J2011-1%22%2C%22par%22%3A%22wxa_abtest%3Do%26ad_od%3Dshare%26utm_source%3Dandroidapp%26utm_medium%3Dappshare%26utm_campaign%3Dt_335139774%26utm_term%3DCopyURL%22%2C%22event_id%22%3A%22MDownLoadFloat_AppArouseA1%22%2C%22mt_xid%22%3A%22%22%2C%22mt_subsite%22%3A%22%22%7D%2C%22SE%22%3A%7B%22mt_subsite%22%3A%22%22%2C%22__jdv%22%3A%22122270672%7Candroidapp%7Ct_335139774%7Cappshare%7CCopyURL%7C1592378834064%22%2C%22unpl%22%3A%22%22%2C%22__jda%22%3A%22122270672.1592378833367223401773.1592378833.1592378833.1592378833.1%22%7D%7D";
        Uri uri = Uri.parse(url);
        //intent.setClassName("com.taobao.taobao","com.taobao.tao.detail.activity.DetailActivity");
        intent.setData(uri);
        startActivity(intent);


    }

    public void show(){
        goods = (Goods) getIntent().getSerializableExtra("why");
    }



}