package com.heguodong.study.templocationdemo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 01438511 on 2018/12/26.
 */

public class IPHelper {

    public static String getHtmlCode(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inputStream = conn.getInputStream();
        byte[] data =readFromInput(inputStream);
        String html = new String(data,"gbk");
        return html;
    }

    public static byte[] readFromInput(InputStream inStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }

    public static Double keepTwoDecimal(double num) {
        BigDecimal bd = new BigDecimal(num);
        Double d = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }
}
