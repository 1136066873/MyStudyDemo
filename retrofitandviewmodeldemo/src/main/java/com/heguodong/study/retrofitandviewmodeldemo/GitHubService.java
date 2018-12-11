package com.heguodong.study.retrofitandviewmodeldemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by 01438511 on 2018/12/11.
 */

public interface GitHubService {

    @GET
    Call<Endpoints> getAllEndpoints(@Url String url);

    @GET("users/{user}")
    Call<Object> getUserInfo(@Path("user") String user);
}
