package com.haier.demo.myroundpicdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import com.haier.demo.myroundpicdemo.helper.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);


        Picasso.with(this).load("https://image.haier.com/cn/xbsy_37860/sybanner_27044/201812/P020181229474858160551.jpg").into(iv);

    }
}
