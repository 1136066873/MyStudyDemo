package com.heguodong.study.retrofitandviewmodeldemo;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 01438511 on 2018/12/11.
 */

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiUrl.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S creatService(Class<S> serviceClass){
        Retrofit retrofit =  builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
