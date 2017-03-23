package com.nick.bb.fitness.repository.impl;

import android.content.Context;

import com.nick.bb.fitness.api.GankApiService;
import com.nick.bb.fitness.api.entity.decor.GankList;
import com.nick.bb.fitness.repository.Repository;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by sharpay on 17-3-22.
 */

public class RepositoryImpl implements Repository{
    private GankApiService mGankApiService;
    private Context mContext;
    public RepositoryImpl(Context context, Retrofit gank) {
        mContext = context;
        mGankApiService = gank.create(GankApiService.class);
    }

    @Override
    public Observable<GankList> getGankList() {
        return mGankApiService.getGankList();
    }
}
