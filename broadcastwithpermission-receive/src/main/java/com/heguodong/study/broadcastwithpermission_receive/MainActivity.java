package com.heguodong.study.broadcastwithpermission_receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static TextView tv_info ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_info = findViewById(R.id.tv_info);
    }

    public static class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {


            if (!TextUtils.isEmpty(intent.getStringExtra("info"))){
                tv_info.setText(intent.getStringExtra("info"));
                tv_info.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }else {
                tv_info.setText("Info in intent is empty");
                tv_info.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        }
    }
}
