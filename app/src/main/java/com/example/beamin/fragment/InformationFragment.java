package com.example.beamin.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.beamin.HttpConnection;
import com.example.beamin.R;
import com.example.beamin.activity.MenuDetailActivity;
import com.example.beamin.activity.MenuListActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class InformationFragment extends Fragment {
    public ListView listView;

    private HttpConnection httpConn = HttpConnection.getInstance();
    private String name;
    private String min;
    private String payment;
    private String phone;
    private String time;
    private String delLocation;
    private String dayoff;
    private String restLocation;
    private int status;
    private TextView timeTv;
    private TextView dayoffTv;
    private TextView phoneTv;
    private TextView delLcationTv;
    private TextView restLocationTv;

    public InformationFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_information, container, false);

        listView = view.findViewById(R.id.menu_detail_listview);
        timeTv = view.findViewById(R.id.fragment_information_time);
        dayoffTv = view.findViewById(R.id.fragment_information_dayoff);
        phoneTv = view.findViewById(R.id.fragment_information_phone);
        delLcationTv = view.findViewById(R.id.fragment_information_del_location);
        restLocationTv = view.findViewById(R.id.fragment_information_rest_location);

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
                JSONObject jsonObject = new JSONObject(response.body().string());
                name = jsonObject.getJSONObject("data").getString("restaurantName");
                min = String.valueOf(jsonObject.getJSONObject("data").getInt("minimum"));
                payment = jsonObject.getJSONObject("data").getString("payment");
                phone = jsonObject.getJSONObject("data").getString("phoneNumber");
                time = jsonObject.getJSONObject("data").getString("businessTime");
                dayoff = jsonObject.getJSONObject("data").getString("dayOff");
                delLocation = jsonObject.getJSONObject("data").getString("deliveryLocation");
                restLocation = jsonObject.getJSONObject("data").getString("restaurantLocation");
                status = jsonObject.getJSONObject("data").getInt("status");
                //JSON Passing
            } catch (JSONException e) {
                e.printStackTrace();
            }

            MenuListActivity.activity.runOnUiThread(new Runnable() {
                public void run() {
                    MenuDetailActivity.detailActivity.nameTv.setText(name);
                    MenuDetailActivity.detailActivity.minTv.setText(min+"원");
                    MenuDetailActivity.detailActivity.payHowTv.setText(payment);
                    timeTv.setText(time);
                    dayoffTv.setText(dayoff);
                    phoneTv.setText(phone);
                    delLcationTv.setText(delLocation);
                    restLocationTv.setText(restLocation);
                }
            });
            //Set MenuListActivity UI to use runOnUiThread for to use callback function
        }
    };
    //Menu Information Callback function
}
//MenuDetailList Fragment