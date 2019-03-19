package com.laotian.contract;

import android.content.Context;

import com.laotian.base.BasePresenter;
import com.laotian.base.BaseView;
import com.laotian.base.ICallback;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;



public interface MainContract {
    interface Model {
        void getWeather(Context context, String location , Lang language, Unit unit, ICallback<String> callback);
    }

    interface View extends BaseView<Presenter> {
        void showWeather(String data);
    }

    interface Presenter extends BasePresenter {
        void getWeather(Context context,String location ,Lang language,Unit unit);
    }
}
