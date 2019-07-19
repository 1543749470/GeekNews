package com.jy.greendao;

import android.util.Log;

import com.example.geennews2.dao.DaoSession;
import com.example.geennews2.dao.MyLikeBeansDao;
import com.jy.beans.MyLikeBeans;
import com.jy.beans.StoriesBean;

import java.util.List;

public class Utils {
    private static final String TAG = "Utils";

    //查询单个
    public static MyLikeBeans queryOne(MyLikeBeans myLikeBeans ){
        DaoSession daoSession = MyApp.getDaoSession();
        MyLikeBeans bean = daoSession.queryBuilder(MyLikeBeans.class)
                .where(MyLikeBeansDao.Properties.Id.eq(myLikeBeans.getId()))
                .build()
                .unique();
        return bean;
    }
    //查询所有
    public static List<MyLikeBeans> queryAll(){
        DaoSession daoSession = MyApp.getDaoSession();
        return daoSession.loadAll(MyLikeBeans.class);
    }
    //添加
    public static void insert(MyLikeBeans myLikeBeans){
        DaoSession daoSession = MyApp.getDaoSession();
        MyLikeBeans myLikeBeans1 = queryOne(myLikeBeans);
        if (myLikeBeans1==null){
            daoSession.insert(myLikeBeans);
        }else{
            Log.e(TAG, "insert: 已存在" );
        }
    }
    //删除
    public static void delete(MyLikeBeans myLikeBeans){
        DaoSession daoSession = MyApp.getDaoSession();
        MyLikeBeans myLikeBeans1 = queryOne(myLikeBeans);

        if (myLikeBeans1!=null){
            daoSession.delete(myLikeBeans1);
        }else{
            Log.e(TAG, "delete: 不存在" );
        }
    }
}
