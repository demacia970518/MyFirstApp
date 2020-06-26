package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class listview extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Goods> goods;
    private Goods goodshttp;
    private List<JDPirce> jdPirceList;
    private List<List<JDPirce>> alljdPirceList;
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd");
    private List<Goods> goodsbitmap;
    private String store;
    private String searchKey;
    private List<byte[]> imglist;
    private byte[] img;
    private String pirces = "暂无";
    private  LinearAdapter linearAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:

                    LinearAdapter linearAdapter = new LinearAdapter(goods, listview.this);
                    recyclerView.setAdapter(linearAdapter);
                    linearAdapter.setMyOnClickListener(new MyOnClickListener() {
                        @Override
                        public void onClick(View view, int position, int child_position) {
                            Intent intent = new Intent(listview.this, webView.class);
                            intent.putExtra("good", goods.get(position));
                            startActivity(intent);
                        }
                    });


            }
            return true;
        }
    });

    Handler handler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                byte[] img = (byte[]) msg.obj;
                imglist.add(img);

            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        recyclerView = (RecyclerView) findViewById(R.id.rv);


        final String category=getIntent().getStringExtra("category");

        imglist = new ArrayList<>();
        //addGoods();
        // show();
        //searchKey =getIntent().getStringExtra("category");
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                //String resp=MyApplication.myClient.get("http://hatlate.cn/web/search",UrlParam.addParam("key", searchKey));
                String url =MyApplication.myClient.get("https://apis.juhe.cn/rubbish/category?key=b014ecb9acb4cacc0592ef21b9ad912b");
                try {
                    JSONObject data1 =new JSONObject(url);

                    JSONArray jsonArray =data1.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String id=jsonObject1.getString("name");
                        String name=jsonObject1.getString("explain");
                       // String url =jsonObject1.getString("imgurl");
                        goodshttp =new Goods(name,id,R.drawable.saina);
                        goods.add(goodshttp);
                    }
                    Message message =Message.obtain();
                    message.what=1;

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                /* try {

                    JSONArray jsonArray =new JSONArray(url);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject obj =jsonArray.getJSONObject(i);
                        String name =obj.optString("name","");
                        String


                        long sku=obj.getLong("sku");


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = MyApplication.myClient.sendOkHttp("http://10.20.230.237:8080/web/get/items?key="+category);

                try {
                    Response response = call.execute();
                    goods = new ArrayList<>();
                    alljdPirceList=new ArrayList<>();
                    if (response.isSuccessful()) {
                        Message message = Message.obtain();
                        String jsonStr = response.body().string();
                        Log.d("resss", jsonStr);
                        try {
                            JSONArray jsonArray = new JSONArray(jsonStr);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject obj = jsonArray.getJSONObject(i);
                                Long sku = obj.getLong("sku");
                                String intro = obj.optString("intro", "");
                                String comment = obj.optString("comments", "");
                                //String id=obj.optString("","");
                                //String imgUrl =obj.optString("","");
                                String imgUrl = "http://10.20.230.237:8080/web/gen/img?pt=jd&sku=" + String.valueOf(sku);

                                Call call1 = MyApplication.myClient.sendOkHttp(imgUrl);
                                //String sku =obj.optString("","");
                                Response response1 = call1.execute();
                                //String img =response1.body().string();
                                byte[] img = response1.body().bytes();

                                Call call2=MyApplication.myClient.sendOkHttp("http://10.20.230.237:8080/web/get/prices?pt=jd&sku="+String.valueOf(sku));


                                Response response2 = call2.execute();
                                List<JDPirce> jdPirceList =new ArrayList<>();

                                String dateprice =response2.body().string();
                                Log.d("price",dateprice);

                                JSONArray jsondata =new JSONArray(dateprice);


                                for (int j=0; j<jsondata.length(); j++){
                                    // double price;
                                    JSONObject dateobj =jsondata.getJSONObject(j);
                                    String date = dateobj.optString("day","");
                                    if(date!=""){
                                        Date curtime =new Date(System.currentTimeMillis());
                                        String currentDate =format.format(curtime);
                                        double price =dateobj.optDouble("price");
                                        date=format.format(format.parse(date));
                                        if(date==currentDate){
                                            pirces=String.valueOf(price);
                                        }

                                        jdPirceList.add(new JDPirce(date,price));
                                        Log.d("price",jdPirceList.get(j).toString());

                                    }
                                    else {

                                    }

                                    //pirces=String.valueOf(price);

                                    //JDPirce jdPirce =new JDPirce(day,prices,comment,sku);

                                }

                                alljdPirceList.add(jdPirceList);
                                goodshttp = new Goods(intro, pirces, comment, sku, img);
                                goods.add(goodshttp);

                            }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        recyclerView.setLayoutManager(new LinearLayoutManager(listview.this));
                                         linearAdapter = new LinearAdapter(goods, listview.this);
                                        recyclerView.setAdapter(linearAdapter);
                                        linearAdapter.setMyOnClickListener(new MyOnClickListener() {
                                            @Override
                                            public void onClick(View view, int position, int child_position) {
                                                Intent intent = new Intent(listview.this, webView.class);

                                                Log.d("web",String.valueOf(position));
                                                List<JDPirce> list =alljdPirceList.get(position);
                                                Bundle bundle =new Bundle();
                                                bundle.putSerializable("date", (Serializable) list);
                                                intent.putExtras(bundle);
                                               //intent.putExtra("dateprice", (Serializable)list);
                                                intent.putExtra("good",  goods.get(position));
                                                startActivity(intent);
                                            }
                                        });


                                    }
                                });

                            //message.what = 1;
                            //handler.sendMessage(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();









        swipeRefreshLayout=(SwipeRefreshLayout)findViewById((R.id.fresh));
        swipeRefreshLayout.setColorSchemeResources(R.color.red,R.color.black,R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });



    }


    /* public void addGoods() {
        goods = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Goods good1 = new Goods("这个是西部魔影赛娜","$6300", R.drawable.saina);
            goods.add(good1);
            Goods good2 = new Goods("这个是约德尔人的一大步", "$6300",R.drawable.timo);
            goods.add(good2);
            Goods good3 = new Goods("这个是小法愚人节","$4800", R.drawable.xiaofa);
            goods.add(good3);
            Goods good4 = new Goods("这个是卢锡安未来战士","$4800", R.drawable.lucian);
            goods.add(good4);


        }


    }*/


  /* public Bitmap getImage(String str){




   }*/


    public void getItemPrice( int position)  {


    }




  public void getOk(){
        OkHttpClient okHttpClient =new OkHttpClient();
        //String url="http://guolin.tech/api/china";
        String url ="https://apis.juhe.cn/rubbish/category?key=b014ecb9acb4cacc0592ef21b9ad912b";
        final Request request =new Request.Builder().url(url).build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Message message =Message.obtain();
                if(response.isSuccessful()){


                    try {
                        message.what=1;
                        String str =response.body().string();
                        JSONObject data = new JSONObject(str);
                        message.obj= data;
                        handler.sendMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
                else {
                    handler.sendEmptyMessage(0);
                }

            }
        });

    }

    public void show(){
      /*  goods=new ArrayList<>();
        Intent intent =getIntent();
        Bundle bundle =intent.getExtras();*/
        goods = (List<Goods>) getIntent().getSerializableExtra("why");
    }
}