package com.laotian.contract;

import android.content.Context;

import com.laotian.base.BasePresenter;
import com.laotian.base.BaseView;
import com.laotian.base.ICallback;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Base;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;


public interface MainContract {
    interface Model {
        void getWeather(Context context, String location , Lang language, Unit unit, ICallback<List<Now>> callback);
        void getTime(String date,ICallback<String> callback);
    }

    interface View extends BaseView<Presenter> {
        void showWeather(List<Now> data);
        void showtime(String time);
    }

    interface Presenter extends BasePresenter {
        void getWeather(Context context,String location ,Lang language,Unit unit);

        void getTime(String date);
    }
}
