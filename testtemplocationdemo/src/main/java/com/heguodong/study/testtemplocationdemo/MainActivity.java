package com.heguodong.study.testtemplocationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView tv_info;
    private Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("heguodong"," 当前代码运行在什么线程？"  + Thread.currentThread());
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
        tv_info = findViewById(R.id.tv_info);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:

                sendBroadcastToGetLatitudeAndLongitude();

                break;
        }

    }

    private void sendBroadcastToGetLatitudeAndLongitude() {
        tv_info.setText("定位中，请稍后...");
        Intent intent = new Intent();
        intent.setAction(ActionConstants.ACTION_TO_GET_LATITUDE_AND_LONGITUDE);
        sendBroadcast(intent);
    }

    public static class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ActionConstants.ACTION_GET_LATITUDE_AND_LONGITUDE_FAILURE)){
                if (intent.getBundleExtra("location") != null){
                    Bundle locationData = intent.getBundleExtra("location");
                    String reason = locationData.getString("reason");
                    Log.d("heguodong","经纬度信息获取失败 -> reason : " + reason + "当前代码运行在什么线程？"  + Thread.currentThread());
                    tv_info.setText("经纬度信息获取失败 -> reason : " + reason );
                }
            }else if (intent != null && intent.getAction().equals(ActionConstants.ACTION_GET_LATITUDE_AND_LONGITUDE_SUCCESS)){
                if (intent.getBundleExtra("location") != null){
                    Bundle locationData = intent.getBundleExtra("location");
                    String latitude = locationData.getString("latitude");
                    String longitude = locationData.getString("longitude");
                    Log.d("heguodong","经纬度信息获取成功  ->  latitude : " + latitude + "   ;  longitude  :  " + longitude + "当前代码运行在什么线程？"  + Thread.currentThread());
                    tv_info.setText("经纬度信息获取成功  ->  latitude : " + latitude + "   ;  longitude  :  " + longitude);
                }
            }
        }
    }
}
