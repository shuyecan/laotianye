package com.laotian;

import android.app.Application;

import butterknife.ButterKnife;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;


public class Myappcontract extends Application {
    private static Myappcontract myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        HeConfig.init("HE1903162014581412", "fc0e5a5ebf5f4e44b55b9e7db94a4226");
    }

    public static Myappcontract getInstance(){
        return myApplication;
    }
}
