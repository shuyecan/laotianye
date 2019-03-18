package com.laotian.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

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
       HeWeather.getWeatherNow(context,language, unit, new HeWeather.OnResultWeatherNowBeanListener() {
           @Override
           public void onError(Throwable throwable) {
               callback.onError(""+throwable);
           }

           @Override
           public void onSuccess(List<Now> list) {
               callback.onSucceed(new Gson().toJson(list));
           }
       });
    }


}
