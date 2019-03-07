package com.example.beamin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.beamin.R;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapResultActivity extends AppCompatActivity implements MapView.MapViewEventListener, View.OnClickListener {
    private MapView mapView;
    private String name;
    private String address;
    private double x;
    private double y;
    private EditText detailEt;
    private Button finishBt;
    private TextView addrTv;
    private ImageView backTv;
    private ImageView marker;
    private ViewGroup mapViewContainer;

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_result);

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        address = intent.getExtras().getString("address");
        x = intent.getExtras().getDouble("x");
        y = intent.getExtras().getDouble("y");
        //Bring to values from MapSearchActivity

        mapViewContainer = (ViewGroup) findViewById(R.id.map_show);
        detailEt = findViewById(R.id.map_result_detail_addr_et);
        finishBt = findViewById(R.id.map_result_finish_tv);
        addrTv = findViewById(R.id.map_result_default_addr_tv);
        backTv = findViewById(R.id.map_result_back_tv);
        marker = findViewById(R.id.marker_result);

        mapView = new MapView(this);
        mapViewContainer.addView(mapView);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(x, y), 2, true);
        //Set mapview

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("longitude", String.valueOf(y));
        editor.putString("latitude", String.valueOf(x));
        editor.commit();
        //Initialize basic latitude, longitude for sharedpreference

        mapView.setMapViewEventListener(this);
        backTv.setOnClickListener(this);
        finishBt.setOnClickListener(this);
        //Click event Listener

        marker.bringToFront();
        //Bring to marker from layout
    }


    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        Geocoder geo = new Geocoder(this, Locale.getDefault());
        List<Address> names = null;
        try {
            names = geo.getFromLocation( mapView.getMapCenterPoint().getMapPointGeoCoord().latitude,  mapView.getMapCenterPoint().getMapPointGeoCoord().longitude, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (names.size() > 0) {
            String tmp = names.get(0).getAddressLine(0);
            if(tmp.contains("대한민국 ")){
                tmp = tmp.replace("대한민국 ", "");
            }
            if(tmp.contains("서울특별시")){
                tmp = tmp.replace("서울특별시 ", "");
            }
            //Process long address line

            SharedPreferences pref = getSharedPreferences("map", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("addressMain", tmp);
            editor.commit();
            //Set new address

            addrTv.setText(tmp);
        }
    }
    //Set address when drag mapview

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.map_result_back_tv) {
            finish();
        } else if (v.getId() == R.id.map_result_finish_tv) {
            SharedPreferences pref = getSharedPreferences("map", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("addressMain", addrTv.getText().toString());
            editor.putString("addressDetail", detailEt.getText().toString());
            editor.putString("x", String.valueOf(x));
            editor.putString("y", String.valueOf(y));
            editor.commit();
            finish();
        }
        //store map information
    }


}
