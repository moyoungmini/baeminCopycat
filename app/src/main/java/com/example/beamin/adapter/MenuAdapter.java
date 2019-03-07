package com.example.beamin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.beamin.fragment.ListFragmentEight;
import com.example.beamin.fragment.ListFragmentFive;
import com.example.beamin.fragment.ListFragmentFour;
import com.example.beamin.fragment.ListFragmentNine;
import com.example.beamin.fragment.ListFragmentOne;
import com.example.beamin.fragment.ListFragmentSeven;
import com.example.beamin.fragment.ListFragmentSix;
import com.example.beamin.fragment.ListFragmentThree;
import com.example.beamin.fragment.ListFragmentTwo;

public class MenuAdapter extends FragmentStatePagerAdapter {
    public static ListFragmentOne one = new ListFragmentOne();
    public static ListFragmentTwo two = new ListFragmentTwo();
    public static ListFragmentThree three = new ListFragmentThree();
    public static ListFragmentFour four = new ListFragmentFour();
    public static ListFragmentFive five = new ListFragmentFive();
    public static ListFragmentSix six = new ListFragmentSix();
    public static ListFragmentSeven seven = new ListFragmentSeven();
    public static ListFragmentEight eight = new ListFragmentEight();
    public static ListFragmentNine nine = new ListFragmentNine();

    public MenuAdapter(FragmentManager _fm) {
        super(_fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return one;
            case 1:
                return two;
            case 2:
                return three;
            case 3:
                return four;
            case 4:
                return five;
            case 5:
                return six;
            case 6:
                return seven;
            case 7:
                return eight;
            case 8:
                return nine;
        }
        return null;
    }
    //Must make each fragment
    @Override
    public int getCount() {
        return 9;
    }
}
//MenuListActivity adapter