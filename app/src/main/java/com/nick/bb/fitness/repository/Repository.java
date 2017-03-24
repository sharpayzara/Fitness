package com.nick.bb.fitness.repository;

import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.api.entity.decor.GankList;

import rx.Observable;

/**
*  desc
*  @author  yangjh
*  created at  17-3-22 下午3:24
*/

public interface Repository {
    Observable<GankList> getGankList();

    Observable<BeautyList> getBeautyList(int page,int size);
}
