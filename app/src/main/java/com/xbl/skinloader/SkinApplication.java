package com.xbl.skinloader;

import android.app.Application;

import com.xbl.skinlib.loader.SkinManager;

public class SkinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }
}
