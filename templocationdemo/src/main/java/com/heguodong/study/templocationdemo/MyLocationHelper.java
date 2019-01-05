package com.heguodong.study.templocationdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by 01438511 on 2018/12/26.
 */

public class MyLocationHelper {

    private static Context mContext ;

    private ExecutorService executorService ;

    private static MyLocationHelper locationHelper ;

    private static boolean isNeedGetLocation = true;

    private MyLocationHelper(Context context) {
        this.mContext = context;
        //executorService = Executors.newFixedThreadPool(1);
    }

    public static MyLocationHelper getInstance(Context mContext){
        if (locationHelper == null){
            synchronized (MyLocationHelper.class){
                locationHelper = new MyLocationHelper(mContext);
            }
        }
        return locationHelper;
    }

    public void GetCurrentLatitudeAndLongitude(){
/*        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                try {
                    final String responseData = IPHelper.getHtmlCode("https://ipinfodb.com/");
                    extractMessage(responseData,mContext);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("heguodong","经纬度信息获取失败  ->  InterruptedException : " + e.getLocalizedMessage());
                    sendBroadcastToOtherAppsGetLocationFailure(mContext,e.getLocalizedMessage());
                }
            }
        };
        executorService.execute(runnable);*/
        if (isNeedGetLocation){
            isNeedGetLocation = false;
            LocationThread locationThread = new LocationThread();
            locationThread.start();
            try {
                locationThread.join();
            } catch (InterruptedException e) {
                isNeedGetLocation = true;
                e.printStackTrace();
                sendBroadcastToOtherAppsGetLocationFailure(mContext,e.getLocalizedMessage());
            }
        }else {
            //请等待上次定位结果
            Log.i("heguodong","请等待上次定位结果" );
        }


    }

    static class LocationThread extends Thread {

        @Override
        public void run() {
            try {
                final String responseData = IPHelper.getHtmlCode("https://ipinfodb.com/");
                extractMessage(responseData,mContext);
            } catch (Exception e) {
                isNeedGetLocation = true;
                e.printStackTrace();
                sendBroadcastToOtherAppsGetLocationFailure(mContext,e.getLocalizedMessage());
            }
        }
    }

    private static List<String> extractMessage(String msg ,Context mContext) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '(') {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (msg.charAt(i) == ')') {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }
        String[] strs = list.get(0).split(", ");
        Double mLat = IPHelper.keepTwoDecimal(Double.parseDouble(strs[0]));
        Double mLon = IPHelper.keepTwoDecimal(Double.parseDouble(strs[1]));
        Log.i("heguodong","经纬度信息获取成功  ->  latitude : " + mLat + "   ;  longitude  :  " + mLon);
        //Send a broadcast to other apps
        sendBroadcastToOtherAppsGetLocationSuccess(mContext, mLat, mLon);
        isNeedGetLocation = true;
        return list;
    }

    private static void sendBroadcastToOtherAppsGetLocationSuccess(Context context, Double mLat, Double mLon) {
        Bundle bundle = new Bundle();
        bundle.putString("latitude","" + mLat);
        bundle.putString("longitude","" + mLon);
        Intent intent = new Intent();
        intent.setAction(ActionConstants.ACTION_GET_LATITUDE_AND_LONGITUDE_SUCCESS);
        intent.putExtra("location",bundle);
        context.sendBroadcast(intent);
    }

    private static void sendBroadcastToOtherAppsGetLocationFailure(Context context,String reason) {
        Bundle bundle = new Bundle();
        bundle.putString("reason","" + reason);
        Intent intent = new Intent();
        intent.setAction(ActionConstants.ACTION_GET_LATITUDE_AND_LONGITUDE_FAILURE);
        intent.putExtra("location",bundle);
        context.sendBroadcast(intent);
    }
}
