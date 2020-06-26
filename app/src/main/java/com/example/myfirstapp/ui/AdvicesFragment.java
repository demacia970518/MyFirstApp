package com.example.myfirstapp.ui;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.AdviceAdapter;
import com.example.myfirstapp.Advices;
import com.example.myfirstapp.GetLIstData;
import com.example.myfirstapp.Goods;
import com.example.myfirstapp.MyOnClickListener;
import com.example.myfirstapp.OpenUrl;
import com.example.myfirstapp.R;
import com.example.myfirstapp.SuggGoods;
import com.example.myfirstapp.listview;
import com.example.myfirstapp.webView;

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

public class AdvicesFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<SuggGoods> advices;
    private List<Goods> goods;
    private Goods goodhttp;
    private List<Goods> goodList;
    private SuggGoods suggGoods;
    private static final String TAG ="why" ;
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    goodList=new ArrayList<>();


                    try {
                        JSONObject data1 = (JSONObject) msg.obj;

                        JSONArray jsonArray =data1.getJSONArray("result");
                        for(int i=0;i<jsonArray.length();i++)
                        {

                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String id=jsonObject1.getString("name");
                            String name=jsonObject1.getString("explain");
                            String url =jsonObject1.getString("imgurl");
                            goodhttp =new Goods(name,id,R.drawable.saina);
                            goodList.add(goodhttp);

                        }

                        Intent intent =new Intent(getActivity(),listview.class);
                        intent.putExtra("why",(Serializable) (goodList));
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
            return true;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.activity_listview,container,false);
        initRecyclerView();
       return  view;

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView)view.findViewById(R.id.rv);
        addGoods();
        suggestItem();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdviceAdapter adviceAdapter =new AdviceAdapter(goods,advices,getActivity());
        adviceAdapter.setMyOnClickListener(new MyOnClickListener() {
            @Override
            public void onClick(View view, int position, int child_position) {
                if (position == 0) {
                    if(child_position==0){

                    }
                    if(child_position==1){
                        Intent intent =new Intent(getActivity(),listview.class);
                        intent.putExtra("category","笔记本电脑");
                        startActivity(intent);


                    }

                    if(child_position==2){
                        Intent intent = new Intent(getActivity(), OpenUrl.class);
                        startActivity(intent);
                    }


                } else {

                }
            }
        });
        recyclerView.setAdapter(adviceAdapter);
    }

    private void suggestItem() {
        advices=new ArrayList<>();
        for (int i=0;i<2;i++){
            SuggGoods suggGoods =new SuggGoods(R.drawable.lucian,"lucian");
            advices.add(suggGoods);
            SuggGoods suggGoods1=new SuggGoods(R.drawable.xiaofa,"weiga");
            advices.add(suggGoods1);
            SuggGoods suggGoods2 =new SuggGoods(R.drawable.timo,"teemo");
            advices.add(suggGoods2);
            SuggGoods suggGoods3=new SuggGoods(R.drawable.saina,"saina");
            advices.add(suggGoods3);
        }
    }

    private void addGoods() {
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
}
