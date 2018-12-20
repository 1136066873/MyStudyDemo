package com.heguodong.study.broadcastwithpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_send_broadcast_to_self).setOnClickListener(this);
        findViewById(R.id.btn_send_broadcast_to_another_app).setOnClickListener(this);
        tv_info = findViewById(R.id.tv_info);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send_broadcast_to_self:

                sendBroadcastWithPermissionToSelf(this);

                break;

            case R.id.btn_send_broadcast_to_another_app:

                sendBroadcastWithPermissionToOtherApp(this);

                break;

        }
    }

    private void sendBroadcastWithPermissionToOtherApp(Context context) {
        Intent intent = new Intent();
        intent.putExtra("info","This message is send from com.heguodong.study.broadcastwithpermission.MainActivity");
        intent.setAction(Constant.ANOTHER_APP_ACTION);
        intent.setPackage(Constant.ANOTHER_APP_PACKAGENAME);
        context.sendBroadcast(intent);


        //context.sendBroadcast(intent,Constant.BROADCAST_USES_PERMISSION_OPERATING_RECEIVE_APP);
    }

    private void sendBroadcastWithPermissionToSelf(Context context) {
        Intent intent = new Intent();
        intent.putExtra("info","This message is send from com.heguodong.study.broadcastwithpermission.MainActivity");
        intent.setAction(Constant.MY_APP_ACTION);
        intent.setPackage(getPackageName());
        context.sendBroadcast(intent);


        //context.sendBroadcast(intent,Constant.BROADCAST_USES_PERMISSION_OPERATING_MY_APP);
    }

    public static class MyBroadcastReceiver extends BroadcastReceiver{

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
