package com.example.beamin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.beamin.R;

public class NavFragment extends Fragment {
    private FrameLayout navigationDrawer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navigationDrawer = (FrameLayout) inflater.inflate(R.layout.fragment_drawer, container, false);
        return navigationDrawer;
    }
}