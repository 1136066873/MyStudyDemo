package com.heguodong.study.locationdemo;

import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView tv;
    public MyLocationListener myLocationListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv.setOnClickListener(this);
        myLocationListener = new MyLocationListener(){
            @Override
            public void onLocationChanged(Location location) {
                super.onLocationChanged(location);
                tv.setText("纬度：" + location.getLatitude() + " 经度：" + location.getLongitude());
            }
        };


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //检查定位是否被打开

        boolean networkIsOpen = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean gpsIsOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean passiveIsOpen = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);


        Location location = LocationUtil.getInstance( MainActivity.this ,myLocationListener).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            Log.d( "heguodong", address );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtil.getInstance( this ,myLocationListener).removeLocationUpdatesListener();
    }

    @Override
    public void onClick(View v) {

    }
}
