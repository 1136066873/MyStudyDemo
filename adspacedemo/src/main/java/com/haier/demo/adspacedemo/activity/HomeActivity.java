package com.haier.demo.adspacedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import com.haier.demo.adspacedemo.adapter.BannerBean;
import com.haier.demo.adspacedemo.adapter.ContentFragmentAdapter;
import com.haier.demo.adspacedemo.fragment.CardFragment;
import com.haier.demo.adspacedemo.library.OrientedViewPager;
import com.haier.demo.adspacedemo.library.transformer.VerticalStackTransformer;

import java.util.ArrayList;
import java.util.List;
import com.haier.demo.adspacedemo.R;

/**
 * Created by Nate on 2016/7/22.
 */
public class HomeActivity extends AppCompatActivity {

    private OrientedViewPager mOrientedViewPager;
    private ContentFragmentAdapter mContentFragmentAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mOrientedViewPager = findViewById(R.id.view_pager);


        //制造数据
        for (int i = 0; i < 10; i++) {
            BannerBean bean = new BannerBean("https://image.haier.com/cn/xbsy_37860/sybanner_27044/201812/P020181229474858160551.jpg",
                    "https://www.haier.com/cn/ehaier/");
            mFragments.add(CardFragment.newInstance((i + 1),bean.getmCurrentBinnerUrl(),bean.getmCurrentBinnerAdvertisingLink()));
        }

        mContentFragmentAdapter = new
                ContentFragmentAdapter(getSupportFragmentManager(), mFragments);
        //设置viewpager的方向为竖直
        mOrientedViewPager.setOrientation(OrientedViewPager.Orientation.VERTICAL);
        //设置limit
        mOrientedViewPager.setOffscreenPageLimit(2);
        //设置transformer
        mOrientedViewPager.setPageTransformer(true, new VerticalStackTransformer(getApplicationContext()));
        mOrientedViewPager.setAdapter(mContentFragmentAdapter);

    }
}
