package com.heguodong.study.templocationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 01438511 on 2018/12/27.
 * This BroadcastReceiver handles requests for latitude and longitude
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && ActionConstants.ACTION_TO_GET_LATITUDE_AND_LONGITUDE.equals(intent.getAction())){
            MyLocationHelper.getInstance(context).GetCurrentLatitudeAndLongitude();
        }
    }
}
