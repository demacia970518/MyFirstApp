package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.Goods;
import com.example.myfirstapp.LinearAdapter;
import com.example.myfirstapp.MyOnClickListener;
import com.example.myfirstapp.R;
import com.example.myfirstapp.listview;
import com.example.myfirstapp.webView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<Goods> goods;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.activity_listview,container,false);
        addGoods();
        initRecyclerView();
        return view;


    }



    private void initRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearAdapter linearAdapter= new LinearAdapter(goods, getActivity());
        recyclerView.setAdapter(linearAdapter);
        linearAdapter.setMyOnClickListener(new MyOnClickListener() {
            @Override
            public void onClick(View view, int position, int child_position) {
                Intent intent =new Intent(getActivity(), webView.class);
                startActivity(intent);
            }
        });
    }
    public void addGoods() {
        goods = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Goods good1 = new Goods("这个是西部魔影赛娜", "$6300", R.drawable.saina);
            goods.add(good1);
            Goods good2 = new Goods("这个是约德尔人的一大步", "$6300", R.drawable.timo);
            goods.add(good2);
            Goods good3 = new Goods("这个是小法愚人节", "$4800", R.drawable.xiaofa);
            goods.add(good3);
            Goods good4 = new Goods("这个是卢锡安未来战士", "$4800", R.drawable.lucian);
            goods.add(good4);


        }
    }
}
