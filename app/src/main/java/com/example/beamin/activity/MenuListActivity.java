package com.example.beamin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.beamin.adapter.MenuAdapter;
import com.example.beamin.R;

public class MenuListActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private MenuAdapter menuAdapter;
    private ImageView backView;
    private int tab;
    public static TextView menuView;
    public static MenuListActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        activity = this;

        backView = findViewById(R.id.menu_list_back_tv);
        viewPager = (ViewPager) findViewById(R.id.menu_list_pager);
        mTabLayout = (TabLayout) findViewById(R.id.menu_list_tab);
        menuView = findViewById(R.id.menu_list_name_tv);

        mTabLayout.addTab(mTabLayout.newTab().setText("한식"));
        mTabLayout.addTab(mTabLayout.newTab().setText("분식"));
        mTabLayout.addTab(mTabLayout.newTab().setText("돈까스-회-일식"));
        mTabLayout.addTab(mTabLayout.newTab().setText("치킨"));
        mTabLayout.addTab(mTabLayout.newTab().setText("피자"));
        mTabLayout.addTab(mTabLayout.newTab().setText("중국집"));
        mTabLayout.addTab(mTabLayout.newTab().setText("패스트푸드"));
        mTabLayout.addTab(mTabLayout.newTab().setText("족발-보쌈"));
        mTabLayout.addTab(mTabLayout.newTab().setText("찜-탕"));
        //Add tab


        menuAdapter = new MenuAdapter(getSupportFragmentManager());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        menuView.setText("한식");
                        break;
                    case 1:
                        menuView.setText("분식");
                        break;
                    case 2:
                        menuView.setText("돈까스-회-일식");
                        break;
                    case 3:
                        menuView.setText("치킨");
                        break;
                    case 4:
                        menuView.setText("피자");
                        break;
                    case 5:
                        menuView.setText("중국집");
                        break;
                    case 6:
                        menuView.setText("패스트푸드");
                        break;
                    case 7:
                        menuView.setText("족발-보쌈");
                        break;
                    case 8:
                        menuView.setText("찜-탕");
                        break;
                    default:
                        break;
                }
            }
            //Set MenuListActivity textview of menuView when select tab

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //Set viewpager change listener

        Intent intent = getIntent();
        tab = intent.getIntExtra("tab",0);
        //read tab from mainactivity grid item to click

        backView.setOnClickListener(this);
        viewPager.setAdapter(menuAdapter);

        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        mTabLayout.getTabAt(tab).select();
                    }
                },0);
        //Set tab Select

        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
            }});
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.menu_list_back_tv){
            finish();
        }
    }
}