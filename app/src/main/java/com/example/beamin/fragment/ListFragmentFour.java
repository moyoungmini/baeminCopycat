package com.example.beamin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.beamin.HttpConnection;
import com.example.beamin.R;
import com.example.beamin.activity.MenuDetailActivity;
import com.example.beamin.activity.MenuListActivity;
import com.example.beamin.adapter.MenuListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListFragmentFour extends Fragment {
    public ListView listView;
    public MenuListAdapter menuListAdapter;
    private HttpConnection httpConn = HttpConnection.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_list, container, false);
        listView = view.findViewById(R.id.menu_list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int num = menuListAdapter.itemList.get(position).numId;
                Intent intent  = new Intent(getContext(), MenuDetailActivity.class);
                intent.putExtra("key",num);
                startActivity(intent);
            }
        });

        try {
            httpConn.menuList("chicken",callback);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("Error", "콜백오류:"+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {

            try {
                menuListAdapter = new MenuListAdapter(getContext());
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONArray arr = jsonObject.getJSONArray("data");
                for(int i=0;i<arr.length();i++){
                    //String img = arr.getJSONObject(i).getString("imageURL");
                    String name = arr.getJSONObject(i).getString("restaurantName");
                    String menu = arr.getJSONObject(i).getString("mainMenu");
                    int key = arr.getJSONObject(i).getInt("restaurantNumber");
                    menuListAdapter.addList(0,name,menu,key);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            menuListAdapter.notifyDataSetChanged();
            MenuListActivity.activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    listView.setAdapter(menuListAdapter);
                    // Stuff that updates the UI

                }
            });
        }
    };
}
//Reference ListFragmentOne remark