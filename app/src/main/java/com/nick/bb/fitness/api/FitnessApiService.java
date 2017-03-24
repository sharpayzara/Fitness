package com.nick.bb.fitness.api;

import com.nick.bb.fitness.api.entity.BeautyBean;
import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.api.entity.decor.GankList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sharpay on 17-3-22.
 */

public interface FitnessApiService {
    @GET("api/data/Android/10/1")
    Observable<GankList> getGankList();

    @GET("api/data/福利/{size}/{page}")
    Observable<BeautyList> getBeautyList(@Path("page") int page, @Path("size") int size);
}
