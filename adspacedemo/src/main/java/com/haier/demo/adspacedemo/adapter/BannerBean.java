package com.haier.demo.adspacedemo.adapter;

import java.io.Serializable;

/**
 * Created by 01438511 on 2019/1/5.
 */

public class BannerBean implements Serializable {

    private String mCurrentBinnerUrl ;

    private String mCurrentBinnerAdvertisingLink ;

    public BannerBean(String currentBinnerUrl, String currentBinnerAdvertisingLink){
        mCurrentBinnerUrl = currentBinnerUrl;
        mCurrentBinnerAdvertisingLink = currentBinnerAdvertisingLink;
    }

    public String getmCurrentBinnerUrl() {
        return mCurrentBinnerUrl;
    }

    public String getmCurrentBinnerAdvertisingLink() {
        return mCurrentBinnerAdvertisingLink;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "mCurrentBinnerUrl='" + mCurrentBinnerUrl + '\'' +
                ", mCurrentBinnerAdvertisingLink='" + mCurrentBinnerAdvertisingLink + '\'' +
                '}';
    }
}
