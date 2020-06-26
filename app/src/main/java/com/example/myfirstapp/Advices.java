package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myfirstapp.ui.AdvicesFragment;
import com.example.myfirstapp.ui.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Advices extends AppCompatActivity {
    private static final String TAG ="asd" ;
    private SearchView searchView;
    private static boolean isExit=false;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment1,fragment2;
    private static Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            isExit=false;
        }
    };



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_advices);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        initBottomNavigation();




        searchView=findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(true);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent =new Intent(Advices.this,listview.class);
                intent.putExtra("category",query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


    }






    public void initBottomNavigation(){
        fragment1=new AdvicesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.recyclerView,fragment1).show(fragment1).commit();
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();

                    switch (menuItem.getItemId()){
                        case  R.id.navigation_like:
                            transaction.hide(fragment1);
                            if(fragment2==null){
                                Log.d(TAG,"text1crate");
                              fragment2=new HomeActivity();
                                transaction.add(R.id.recyclerView,fragment2).commit();
                            }
                            else {
                                Log.d(TAG,"text1show");
                                transaction.show(fragment2).commit();
                            }
                            break;


                        case R.id.navigation_person:
                            break;

                        case R.id.navigation_home:
                            if(fragment2!=null){
                                transaction.hide(fragment2);
                            }
                            if(fragment1==null){
                                fragment1=new AdvicesFragment();
                                transaction.add(R.id.recyclerView,fragment1).commit();
                            }
                            else {
                                Log.d(TAG,"text");
                                transaction.show(fragment1).commit();
                            }
                            break;

                    }
                    return true;
                }
            });
    }








    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            Toast toast = Toast.makeText(Advices.this, "再按一次退出", Toast.LENGTH_SHORT);
            if (isExit == false) {
                toast.show();
                isExit = true;
                handler.sendEmptyMessageDelayed(0,2000);
            }
            else {
                toast.cancel();
                finish();
            }
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.game_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }



    }








