package com.example.beamin.fragment;

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
import com.example.beamin.adapter.MenuDetailListAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MenuFragment extends Fragment {
    public ListView listView;
    public MenuDetailListAdapter menuDetailListAdapter;
    private HttpConnection httpConn = HttpConnection.getInstance();
    private String name;
    private String min;
    private String pay;
    public static String phone;
    private String del;
    private String location;

    public MenuFragment() {
        menuDetailListAdapter = new MenuDetailListAdapter(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_menu, container, false);
        listView = view.findViewById(R.id.menu_detail_listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String tmpPrice = menuDetailListAdapter.itemList.get(position).price;
                    String tmpMenu = menuDetailListAdapter.itemList.get(position).menu;
            }
        });

        httpConn.menuDetail(String.valueOf(MenuDetailActivity.key),callback);

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
                menuDetailListAdapter = new MenuDetailListAdapter(getContext());
                JSONObject jsonObject = new JSONObject(response.body().string());
                name = jsonObject.getJSONObject("data").getString("restaurantName");
                min = String.valueOf(jsonObject.getJSONObject("data").getInt("minimum"));
                pay = jsonObject.getJSONObject("data").getString("payment");
                phone = jsonObject.getJSONObject("data").getString("phoneNumber");
                del = jsonObject.getJSONObject("data").getString("deliveryLocation");
                location = jsonObject.getJSONObject("data").getString("restaurantLocation");

                JSONArray arr = jsonObject.getJSONObject("data").getJSONArray("menu");
                for(int i=0;i<arr.length();i++){
                    //String img = arr.getJSONObject(i).getString("imageURL");
                    String menu = arr.getJSONObject(i).getString("menuName");
                    String price = String.valueOf(arr.getJSONObject(i).getInt("price"));
                    menuDetailListAdapter.addList(0, menu,price);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Json Passing

            MenuListActivity.activity.runOnUiThread(new Runnable() {
                public void run() {
                    MenuDetailActivity.detailActivity.nameTv.setText(name);
                    MenuDetailActivity.detailActivity.minTv.setText(min+"원");
                    MenuDetailActivity.detailActivity.payHowTv.setText(pay);
                    menuDetailListAdapter.notifyDataSetChanged();
                    listView.setAdapter(menuDetailListAdapter);
                }
            });
            //use runOnUiThread for callback function
        }
    };
}
//MenuDetailList menu Fragment