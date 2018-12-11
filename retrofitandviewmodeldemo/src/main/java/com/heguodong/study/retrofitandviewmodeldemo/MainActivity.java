package com.heguodong.study.retrofitandviewmodeldemo;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * http://www.cnblogs.com/mengdd/p/6047948.html
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_getallendpoints,btn_getuserinfo ;
    private TextView tv_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_getallendpoints = findViewById(R.id.btn_getallendpoints);
        btn_getuserinfo = findViewById(R.id.btn_getuserinfo);
        tv_info = findViewById(R.id.tv_info);
        btn_getallendpoints.setOnClickListener(this);
        btn_getuserinfo.setOnClickListener(this);
        tv_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_getallendpoints:

                //请求github api的root url, 得到所有的endpoints:
                requestGithubGetAllEndpoints();

                break;
            case R.id.btn_getuserinfo:

                //请求github api 某一个账号的所有信息
                requestGithubGetUserInfo();

                break;
            case R.id.tv_info:

                tv_info.setText("Cleared");

                break;
        }
    }

    private void requestGithubGetUserInfo() {
        GitHubService gitHubService = ServiceGenerator.creatService(GitHubService.class);

        Call<Object> userInfoCall = gitHubService.getUserInfo("1136066873");

        userInfoCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.e("heguodong","is main thread ? " + (Looper.myLooper() == Looper.getMainLooper()));
                String string  = response.body().toString();
                tv_info.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("heguodong","is main thread ? " + (Looper.myLooper() == Looper.getMainLooper()));
                tv_info.setText(t.getMessage());
                tv_info.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

    }

    private void requestGithubGetAllEndpoints() {
        GitHubService gitHubService = ServiceGenerator.creatService(GitHubService.class);

        Call<Endpoints> allEndpointsCall = gitHubService.getAllEndpoints("");

        allEndpointsCall.enqueue(new Callback<Endpoints>() {
            @Override
            public void onResponse(Call<Endpoints> call, Response<Endpoints> response) {
                Log.e("heguodong","is main thread ? " + (Looper.myLooper() == Looper.getMainLooper()));
                tv_info.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Endpoints> call, Throwable t) {
                Log.e("heguodong","is main thread ? " + (Looper.myLooper() == Looper.getMainLooper()));
                tv_info.setText(t.getMessage());
                tv_info.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

    }
}
