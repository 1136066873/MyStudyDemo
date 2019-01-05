package com.haier.demo.advertisingspacedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.haier.demo.advertisingspacedemo.swipecard.CardConfig;
import com.haier.demo.advertisingspacedemo.swipecard.OverLayCardLayoutManager;
import com.haier.demo.advertisingspacedemo.swipecard.RenRenCallback;
import com.haier.demo.advertisingspacedemo.swipecard.TanTanCallback;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 参考自：https://github.com/mcxtzhang/ZLayoutManager
 *
 * https://github.com/shallcheek/shallcheek_tantan
 *
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private CommonAdapter<SwipeCardBean> mAdapter;
    private List<SwipeCardBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new OverLayCardLayoutManager());

        mRv.setAdapter(mAdapter = new CommonAdapter<SwipeCardBean>(this, mDatas = SwipeCardBean.initDatas(), R.layout.item_swipe_card) {
            public static final String TAG = "zxt/Adapter";

            @Override
            public void convert(ViewHolder viewHolder, SwipeCardBean swipeCardBean) {
                Log.d(TAG, "convert() called with: viewHolder = [" + viewHolder + "], swipeCardBean = [" + swipeCardBean + "]");
                viewHolder.setText(R.id.tvName, swipeCardBean.getName());
                viewHolder.setText(R.id.tvPrecent, swipeCardBean.getPostition() + " /" + mDatas.size());
                Picasso.with(MainActivity.this).load(swipeCardBean.getUrl()).into((ImageView) viewHolder.getView(R.id.iv));
            }
        });

        //初始化配置
        CardConfig.initConfig(this);
        ItemTouchHelper.Callback callback = new TanTanCallback(mRv, mAdapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);


        findViewById(R.id.btnRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.add(new SwipeCardBean(100, "http://news.k618.cn/tech/201604/W020160407281077548026.jpg", "增加的"));
                mAdapter.notifyDataSetChanged();
            }
        });
    }

}
