package com.nick.bb.fitness.mvp.usercase;


import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.executor.PostExecutionThread;
import com.nick.bb.fitness.executor.ThreadExecutor;
import com.nick.bb.fitness.mvp.usercase.base.UseCase;
import com.nick.bb.fitness.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by sharpay on 17-3-22.
 */

public class GetBeautyList  extends UseCase<BeautyList, GetBeautyList.Params> {

    private final Repository mRepository;

    @Inject
    GetBeautyList(Repository repository, ThreadExecutor threadExecutor,
                  PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    public Observable<BeautyList> buildUseCaseObservable(GetBeautyList.Params params) {
        return this.mRepository.getBeautyList(params.page,params.size);
    }


    public static final class Params {
        int page,size;

        public Params(int page, int size) {
            this.page = page;
            this.size = size;
        }
    }

}
