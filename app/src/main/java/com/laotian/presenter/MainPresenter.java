package com.laotian.presenter;

import android.content.Context;

import com.laotian.base.ICallback;
import com.laotian.contract.MainContract;
import com.laotian.model.MainModel;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;


public class MainPresenter implements MainContract.Presenter {
    private  MainContract.View mView;


    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void getWeather(Context context, String location, Lang language, Unit unit) {
        new MainModel().getWeather(context, location, language, unit, new ICallback<String>() {
            @Override
            public void onSucceed(String data) {
                mView.showWeather(data);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }



    @Override
    public void start() {

    }
}
