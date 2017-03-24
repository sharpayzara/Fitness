package com.nick.bb.fitness.repository.impl;

import android.content.Context;

import com.nick.bb.fitness.api.FitnessApiService;
import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.api.entity.decor.GankList;
import com.nick.bb.fitness.repository.Repository;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by sharpay on 17-3-22.
 */

public class RepositoryImpl implements Repository{
    private FitnessApiService mFitnessApiService;
    private Context mContext;
    public RepositoryImpl(Context context, Retrofit retrofit) {
        mContext = context;
        mFitnessApiService = retrofit.create(FitnessApiService.class);
    }

    @Override
    public Observable<GankList> getGankList() {
        return mFitnessApiService.getGankList();
    }

    @Override
    public Observable<BeautyList> getBeautyList(int page, int size) {
        return mFitnessApiService.getBeautyList(page,size);
    }

}
