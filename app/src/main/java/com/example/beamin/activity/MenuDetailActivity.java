package com.example.beamin.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.beamin.R;
import com.example.beamin.adapter.MenuDetailAdapter;
import com.example.beamin.fragment.MenuFragment;

public class MenuDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private MenuDetailAdapter menuAdapter;
    private ImageView backView;
    public static Activity activity;
    public static int key;
    public TextView nameTv;
    public TextView minTv;
    public TextView payHowTv;
    private Button phoneBt;
    public static MenuDetailActivity detailActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        detailActivity=this;
        activity = this;

        nameTv = findViewById(R.id.menu_detail_store_tv);
        minTv = findViewById(R.id.menu_detail_min_price_tv);
        payHowTv = findViewById(R.id.menu_detail_pay_tv);
        backView = findViewById(R.id.menu_detail_back_tv);
        phoneBt = findViewById(R.id.activity_menu_detail_phone_bt);
        mTabLayout = (TabLayout) findViewById(R.id.menu_detail_tab);
        viewPager = (ViewPager) findViewById(R.id.menu_detail_pager);

        mTabLayout.addTab(mTabLayout.newTab().setText("메뉴"));
        mTabLayout.addTab(mTabLayout.newTab().setText("정보"));
        //Add tablayout information

        menuAdapter = new MenuDetailAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(menuAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //set view pager

        Intent intent = getIntent();
        key = intent.getExtras().getInt("key");
        //Get information of clicking store list item

        backView.setOnClickListener(this);
        phoneBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.menu_detail_back_tv){
            finish();
        }
        else if(v.getId() == R.id.activity_menu_detail_phone_bt){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+MenuFragment.phone)); startActivity(intent);
        }
    }
}
