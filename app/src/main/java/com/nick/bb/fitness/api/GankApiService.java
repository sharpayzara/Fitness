package com.nick.bb.fitness.api;

import com.nick.bb.fitness.api.entity.decor.GankList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by sharpay on 17-3-22.
 */

public interface GankApiService {
    @GET("api/data/Android/10/1")
    Observable<GankList> getGankList();
}
