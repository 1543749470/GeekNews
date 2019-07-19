package com.example.geennews2;

import com.jy.beans.ThemeBeans;
import io.reactivex.Observable;

import retrofit2.http.GET;

public interface ThemeApiService {
    String getRe="http://news-at.zhihu.com/";
    @GET("api/4/news/hot")
    Observable<ThemeBeans> getR();
}
