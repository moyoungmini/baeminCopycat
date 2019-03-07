package com.example.beamin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.beamin.R;
import com.example.beamin.data.MenuDetailData;
import java.util.ArrayList;

public class MenuDetailListAdapter extends BaseAdapter {

    public ArrayList<MenuDetailData> itemList = new ArrayList<>();
    public String id;

    public MenuDetailListAdapter(Context con){
        final Context context = con;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<MenuDetailData> getItemList() {
        return itemList;
    }

    public String getId() {
        return id;
    }

    public void setItemList(ArrayList<MenuDetailData> itemList) {
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
            convertView = inflater.inflate(R.layout.menu_detail_item, parent, false);
        }

        TextView menu = convertView.findViewById(R.id.menu_detail_name);
        TextView price = convertView.findViewById(R.id.menu_detail_price);
        ImageView img = convertView.findViewById(R.id.menu_detail_img);
        MenuDetailData userItem = itemList.get(position);
        menu.setText(userItem.menu);
        price.setText(userItem.price+"원");
        img.setImageResource(userItem.img);
        return convertView;
    }
    //read that position information and return information

    public void addList(int img, String menu,String price){
        MenuDetailData item = new MenuDetailData();
        item.menu=menu;
        item.price=price;
        item.img=img;
        itemList.add(item);
    }
    //Add menuDetailList information
}

//MenuFragment Listview Adpater