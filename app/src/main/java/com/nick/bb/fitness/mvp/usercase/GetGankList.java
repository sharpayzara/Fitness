package com.nick.bb.fitness.mvp.usercase;


import com.nick.bb.fitness.api.entity.decor.GankList;
import com.nick.bb.fitness.executor.PostExecutionThread;
import com.nick.bb.fitness.executor.ThreadExecutor;
import com.nick.bb.fitness.mvp.usercase.base.UseCase;
import com.nick.bb.fitness.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by sharpay on 17-3-22.
 */

public class GetGankList extends UseCase<GankList, GetGankList.Params> {

    private final Repository mRepository;

    @Inject
    GetGankList(Repository repository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    public Observable<GankList> buildUseCaseObservable(Params params) {
        return this.mRepository.getGankList(params.page,params.size,params.type);
    }


    public static final class Params {
        int page,size;
        String type;

        public Params(int page, int size, String type) {
            this.page = page;
            this.size = size;
            this.type = type;
        }
    }

}
