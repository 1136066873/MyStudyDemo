package com.heguodong.study.templocationdemo;

import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 01438511 on 2018/12/26.
 */

public class GetTimeZoneFromIP {

    private static double mLat;
    private static double mLon;
    private static String mTimeZone;

    public static String getTheAutoTimeZone() {
        LocationThread locationThread = new LocationThread();
        TimenzoneThread timezoneThread = new TimenzoneThread();
        locationThread.start();
        try {
            locationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timezoneThread.start();
        try {
            timezoneThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "" + mLat + " ; " + mLon;
    }

    static class LocationThread extends Thread {
        @Override
        public void run() {
            try {
                final String responseData = IPHelper.getHtmlCode("https://ipinfodb.com/");
                extractMessage(responseData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class TimenzoneThread extends Thread {
        @Override
        public void run() {
            try {
                URL url = new URL("http://api.geonames.org/timezoneJSON?lat=" + mLat + "&lng=" + mLon + "&username=m3053899");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    String result = new String(IPHelper.readFromInput(inputStream), "gbk");
                    JSONObject object = new JSONObject(result);
                    String timezone = object.getString("timezoneId");
                    mTimeZone = timezone;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> extractMessage(String msg) {
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
        mLat = IPHelper.keepTwoDecimal(Double.parseDouble(strs[0]));
        mLon = IPHelper.keepTwoDecimal(Double.parseDouble(strs[1]));
        return list;
    }
}
