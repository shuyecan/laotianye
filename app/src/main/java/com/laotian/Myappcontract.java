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
        HeConfig.init("HE1903181434251110", "6eea7e4736c54de1a3166427550db17c");
        HeConfig.switchToFreeServerNode();
    }

    public static Myappcontract getInstance(){
        return myApplication;
    }

}
