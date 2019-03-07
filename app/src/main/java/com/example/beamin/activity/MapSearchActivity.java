package com.example.beamin.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.beamin.HttpConnection;
import com.example.beamin.data.MapSearchData;
import com.example.beamin.R;
import com.example.beamin.adapter.MapAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MapSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backTv;
    private EditText searchEt;
    private ImageView searchTv;
    private TextView presentTv;
    private ListView listView;
    public ListView listview;
    public MapAdapter adapter;
    public Context context;
    private HttpConnection httpConn = HttpConnection.getInstance();
    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION=2;
    private Activity activity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);

        context = this;
        activity = this;

        backTv = findViewById(R.id.map_search_back_tv);
        searchEt = findViewById(R.id.map_search_input_et);
        searchTv = findViewById(R.id.map_search_finish_tv);
        presentTv = findViewById(R.id.map_search_present_tv);
        listView = findViewById(R.id.map_search_list_view);

        backTv.setOnClickListener(this);
        searchTv.setOnClickListener(this);
        presentTv.setOnClickListener(this);

        listview = findViewById(R.id.map_search_list_view);
        adapter = new MapAdapter(this);
        listView.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MapSearchData item = (MapSearchData) adapterView.getItemAtPosition(position);

                String name = adapter.itemList.get(position).getName();
                String address = adapter.itemList.get(position).getAddress();
                double x = adapter.itemList.get(position).getX();
                double y = adapter.itemList.get(position).getY();

                Intent intent = new Intent(context, MapResultActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                intent.putExtra("x", x);
                intent.putExtra("y", y);
                startActivity(intent);
                finish();
            }
        });
        //Send ListView Item information to MapResultActivity Class
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_search_back_tv:
                finish();
                break;
            case R.id.map_search_finish_tv:
                adapter.itemList.clear();
                httpConn.mapSearchList(searchEt.getText().toString(), callback);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                break;
            case R.id.map_search_present_tv:
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location userLocation = getMyLocation();
                double latitude=0;
                double longitude=0;
                if(userLocation != null){
                    latitude = userLocation.getLatitude();
                    longitude = userLocation.getLongitude();
                }
                //Get present latitude, longitude

                Geocoder geo = new Geocoder(this, Locale.getDefault());
                List<Address> names = null;
                try {
                    names = geo.getFromLocation(latitude,longitude,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(names.size()!=0) {
                    Intent intent = new Intent(context, MapResultActivity.class);
                    intent.putExtra("name", names.get(0).getAddressLine(0));
                    intent.putExtra("address", names.get(0).getAddressLine(0));
                    intent.putExtra("x", latitude);
                    intent.putExtra("y", longitude);
                    startActivity(intent);
                    finish();
                }
                //Get Address Line for geocoder coordinate
                break;
                //get present location coordinate
        }
    }

    private Location getMyLocation(){
        Location currentLocation = null;
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation();
        }
        else{
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if(currentLocation != null){
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
            }
        }
        return currentLocation;
    }
    //Function to get present location

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("Error", "콜백오류:"+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONArray arr = jsonObject.getJSONArray("documents");

                for(int i=0;i<arr.length();i++){
                    String name = arr.getJSONObject(i).getString("place_name");
                    double x = arr.getJSONObject(i).getDouble("y");
                    double y = arr.getJSONObject(i).getDouble("x");
                    String addr = arr.getJSONObject(i).getString("address_name");
                    adapter.addList(name,addr,x,y);

                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                        }
                    });
                    // changing ui must use ui thread
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //JSON Passing
        }
    };
    //use kakao rest api for to get search list (Kakao Rest API Keyword)
}
