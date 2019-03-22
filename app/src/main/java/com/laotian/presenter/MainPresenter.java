package com.laotian.presenter;

import android.content.Context;

import com.laotian.base.ICallback;
import com.laotian.contract.MainContract;
import com.laotian.model.MainModel;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;


public class MainPresenter implements MainContract.Presenter {
    private  MainContract.View mView;
    private  MainModel mainModel;

    public MainPresenter(MainContract.View mView) {
        mainModel = new MainModel();
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void getWeather(Context context, String location, Lang language, Unit unit) {
         mainModel.getWeather(context, location, language, unit, new ICallback<List<Now>>() {
            @Override
            public void onSucceed(List<Now> data) {
                mView.showWeather(data);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    public void getTime(String date) {
            mainModel.getTime(date, new ICallback<String>() {
                @Override
                public void onSucceed(String data) {
                    mView.showtime(data);
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
