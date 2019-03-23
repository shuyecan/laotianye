package com.laotian.model;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.laotian.base.ICallback;
import com.laotian.contract.MainContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;


public class MainModel implements MainContract.Model {
    private long minute = 60*1000;
    private long hour = 60*minute;
    private long day = 24*hour;
    private long month = 31*day;
    private long year = 12*month;

    public void getWeather(Context context, String location, Lang language, Unit unit, final ICallback<List<Now>> callback) {
        HeWeather.getWeatherNow(context,location,language, unit, new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                callback.onError("" + throwable);
            }

            @Override
            public void onSuccess(List<Now> list) {
                if(list.get(0).getStatus().equals("ok")) {
                    callback.onSucceed(list);
                }
            }
        });
    }

    @Override
    public void getTime(String date, ICallback<String> callback) {
        if (date == null) {
            callback.onError("error");
        }
        SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        long diff = 0;
        long r = 0;
        try{
            Long thetime = df.parse(date).getTime();
             diff = System.currentTimeMillis() - thetime;
        }catch (Exception e){

        }
        if (diff > year) {
            r = (diff / year);
            callback.onSucceed(r+"年前");
        }
        if (diff > month) {
            r = (diff / month);
            callback.onSucceed(r+"个月前");
        }
        if (diff > day) {
            r = (diff / day);
            callback.onSucceed(r+"天前");
        }
        if (diff > hour) {
            r = (diff / hour);
            callback.onSucceed(r+"个小时前");
        }
        if (diff > minute) {
            r = (diff / minute);
            callback.onSucceed(r+"分钟前");
        }else {
            callback.onSucceed("刚刚");
        }
    }
}
