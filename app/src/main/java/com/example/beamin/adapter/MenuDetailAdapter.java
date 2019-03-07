package com.example.beamin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.beamin.fragment.InformationFragment;
import com.example.beamin.fragment.MenuFragment;

public class MenuDetailAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;
    public static MenuFragment fm = new MenuFragment();
    public static InformationFragment fm2 = new InformationFragment();
    public MenuDetailAdapter(FragmentManager _fm, int pageCount) {
        super(_fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            //menu 별로 list adapter 설정
            case 0:
                return fm;
            case 1:
                return fm2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
//MenuDetailActivity TabLayout Adapter