package com.jy.dao;

import android.app.Application;

import com.example.geennews.dao.DaoMaster;
import com.example.geennews.dao.DaoSession;

public class MyApp extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "qq.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();

    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
