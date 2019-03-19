package com.laotian.model;

import android.content.Context;

import com.google.gson.Gson;
import com.laotian.base.ICallback;
import com.laotian.contract.MainContract;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;


public class MainModel implements MainContract.Model {


    @Override
    public void getWeather(Context context, String location, Lang language, Unit unit, final ICallback<String> callback) {
        HeWeather.getWeatherNow(context,location,language, unit, new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                callback.onError("" + throwable);
            }

            @Override
            public void onSuccess(List<Now> list) {
                callback.onSucceed(new Gson().toJson(list));
            }
        });
    }

}
