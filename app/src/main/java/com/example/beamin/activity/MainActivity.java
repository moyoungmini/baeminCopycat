package com.example.beamin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.beamin.adapter.AutoScrollAdapter;
import com.example.beamin.R;
import java.util.ArrayList;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private AutoScrollViewPager autoViewPager;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    public static String userID = "empty";
    public static String userNickname = "empty";
    public static boolean loginSuccess = false;
    public Button loginBt;
    public TextView addressTv;
    public static Context context;
    public static double lon;
    public static double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        lat = 37.505620;
        lon = 126.7088113;
        //Initialize map location

        ArrayList<String> data = new ArrayList<>();
        data.add("http://bucoco.kr/images/banner_image/1.png");
        data.add("http://bucoco.kr/images/banner_image/2.png");
        data.add("http://bucoco.kr/images/banner_image/3.png");
        data.add("http://bucoco.kr/images/banner_image/4.png");
        data.add("http://bucoco.kr/images/banner_image/5.png");
        data.add("http://bucoco.kr/images/banner_image/6.png");
        data.add("http://bucoco.kr/images/banner_image/7.png");
        //Add view Pager data

        autoViewPager = (AutoScrollViewPager) findViewById(R.id.auto_view_pager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(this, data);
        autoViewPager.setAdapter(scrollAdapter);
        autoViewPager.setInterval(3000);
        autoViewPager.startAutoScroll();
        //View Pager Set

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //toolbar set

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview=navigationView.getHeaderView(0);
        loginBt =(Button)headerview.findViewById(R.id.nav_header_login_bt);
        loginBt.setOnClickListener(this);
        //navigation bar set

        addressTv=findViewById(R.id.content_main_address_tv);
        addressTv.setOnClickListener(this);
        addressTv.setText("목2동 534-29");
        //address set

        GridLayout grid = (GridLayout) findViewById(R.id.grid_list);
        int childCount = grid.getChildCount();
        for (int i= 0; i < childCount; i++){
            ImageView container = (ImageView) grid.getChildAt(i);
            grid.getChildAt(i).setOnClickListener(this);
        }
        //gridlayout set

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        if((pref.getString("latitude","")!="") && (pref.getString("longitude","")!="")){
            lat = Double.valueOf(pref.getString("latitude", ""));
            lon = Double.valueOf(pref.getString("longitude", ""));
        }
        //input latitude,longitude to sharedpreferences of pref
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pref = getSharedPreferences("map", MODE_PRIVATE);
        String tmp = pref.getString("addressMain", "");
        if(tmp!=null){
            String _tmp = "";
            int k=0;
            for(int i=0;i<tmp.length() && k<3;i++){
                if(tmp.charAt(i)==' '){
                    k++;
                }
                _tmp+=tmp.charAt(i);
            }
            addressTv.setText(_tmp);
        }
    }
    //recall setting pre address

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //navigation bar menu listener

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!userNickname.equals("empty") && MainActivity.loginSuccess){
            loginBt.setText("로그아웃");
            //login 성공
        }
        SharedPreferences pref = getSharedPreferences("map", MODE_PRIVATE);
        String tmp = pref.getString("addressMain", "");
        if(tmp!=null){
            addressTv.setText(tmp);
        }
    }
    //set whether login or logout

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.nav_header_login_bt) {
            if (loginSuccess) {
                loginSuccess=false;
                MainActivity.userID="empty";
                MainActivity.userNickname = "empty";
                loginBt.setText("로그인");
            }
            else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
        else if(v.getId() == R.id.content_main_address_tv){
            Intent intent = new Intent(this,MapSearchActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent  = new Intent(getApplicationContext(), MenuListActivity.class);
            switch (v.getId()){
                case R.id.main_grid_0:
                    intent.putExtra("tab",0);
                    break;
                case R.id.main_grid_1:
                    intent.putExtra("tab",1);
                    break;
                case R.id.main_grid_2:
                    intent.putExtra("tab",2);
                    break;
                case R.id.main_grid_3:
                    intent.putExtra("tab",3);
                    break;
                case R.id.main_grid_4:
                    intent.putExtra("tab",4);
                    break;
                case R.id.main_grid_5:
                    intent.putExtra("tab",5);
                    break;
                case R.id.main_grid_6:
                    intent.putExtra("tab",6);
                    break;
                case R.id.main_grid_7:
                    intent.putExtra("tab",7);
                    break;
                case R.id.main_grid_8:
                    intent.putExtra("tab",8);
                    break;
            }
            startActivity(intent);
        }
        //Grid layout click listener
    }
}