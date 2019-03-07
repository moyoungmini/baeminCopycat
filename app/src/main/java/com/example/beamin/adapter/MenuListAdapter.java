package com.example.beamin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.beamin.R;
import com.example.beamin.data.MenuData;
import java.util.ArrayList;

public class MenuListAdapter extends BaseAdapter {

    public ArrayList<MenuData> itemList = new ArrayList<>();
    public String id;

    public MenuListAdapter(Context con){
        final Context context = con;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<MenuData> getItemList() {
        return itemList;
    }

    public String getId() {
        return id;
    }

    public void setItemList(ArrayList<MenuData> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_list_item, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.menu_list_item_img);
        TextView store = convertView.findViewById(R.id.menu_list_item_store_tv);
        TextView menu = convertView.findViewById(R.id.menu_list_item_menu_tv);

        MenuData userItem = itemList.get(position);

        /*
        if(userItem.getImgUrl()!=0) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);
            Glide.with(context).load(userItem.getImgUrl()).apply(options).into(img);
        }
        */
        //if read image from server, use this code to use glide library

        img.setImageResource(userItem.getImgUrl());
        store.setText(userItem.getName());
        menu.setText(userItem.getMenu());

        return convertView;
    }

    public void addList(int url, String name,String menu,int key){
        MenuData item = new MenuData(url,name,menu);
        item.numId=key;
        itemList.add(item);
    }
    //Add menuActivityList listview information
}

//MenuListActivity listview adpater