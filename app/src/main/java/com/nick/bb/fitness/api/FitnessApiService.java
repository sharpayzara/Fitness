package com.nick.bb.fitness.api;

import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.api.entity.decor.GankList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sharpay on 17-3-22.
 */

public interface FitnessApiService {
    @GET("api/data/{type}/{size}/{page}")
    Observable<GankList> getGankList(@Path("page") int page, @Path("size") int size, @Path("type") String type);

    @GET("api/data/福利/{size}/{page}")
    Observable<BeautyList> getBeautyList(@Path("page") int page, @Path("size") int size);
}
