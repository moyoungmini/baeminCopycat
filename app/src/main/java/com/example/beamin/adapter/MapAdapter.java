package com.example.beamin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.beamin.data.MapSearchData;
import com.example.beamin.R;
import java.util.ArrayList;

public class MapAdapter extends BaseAdapter {
    public ArrayList<MapSearchData> itemList = new ArrayList<>();
    public String id;

    public MapAdapter(Context con){
        final Context context = con;
    }
    public void setItemList(ArrayList<MapSearchData> itemList) {
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
            convertView = inflater.inflate(R.layout.map_search_data, parent, false);
        }

        TextView name = convertView.findViewById(R.id.map_search_name_tv);
        TextView address = convertView.findViewById(R.id.map_search_address_tv);

        MapSearchData userItem = itemList.get(position);
        name.setText(userItem.getName());
        address.setText(userItem.getAddress());

        return convertView;
    }
    //Read that position information and return view

    public void addList(String name, String address,double x, double y){
        MapSearchData item = new MapSearchData(name,address,x,y);
        itemList.add(item);
    }
}
//MapSearch Listview Adapter