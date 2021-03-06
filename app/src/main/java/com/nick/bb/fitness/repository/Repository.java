package com.nick.bb.fitness.repository;

import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.api.entity.decor.GankList;

import io.reactivex.Observable;


/**
*  desc
*  @author  yangjh
*  created at  17-3-22 下午3:24
*/

public interface Repository {
    Observable<GankList> getGankList(int page, int size, String type);

    Observable<BeautyList> getBeautyList(int page,int size);
}
