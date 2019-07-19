package com.jy.greendao;

import android.app.Application;

import com.example.geennews2.dao.DaoMaster;
import com.example.geennews2.dao.DaoSession;

public class MyApp extends Application {
    private static MyApp context ;
    private static DaoSession daoSession;
    public static MyApp getInstance() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "ww.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
