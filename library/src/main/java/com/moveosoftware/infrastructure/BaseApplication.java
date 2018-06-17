package com.moveosoftware.infrastructure;

import android.app.Application;

import com.moveosoftware.infrastructure.mvp.model.network.ApiConfig;
import com.moveosoftware.infrastructure.mvp.model.network.NetworkManager;

/**
 * Created by oferdan-on on 8/26/17
 */

public abstract class BaseApplication extends Application {


    private NetworkManager networkManager;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {

    }

    public abstract ApiConfig getApiConfig();
}
