package com.jy.http;

import com.jy.bean.BaseResponse;
import com.jy.bean.ListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MySercer {
    @GET
    Observable<BaseResponse<List<ListData>>> get(@Url String url);
}
