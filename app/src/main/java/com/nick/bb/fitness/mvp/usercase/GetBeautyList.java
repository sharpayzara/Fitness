package com.nick.bb.fitness.mvp.usercase;


import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.mvp.usercase.base.UseCase;
import com.nick.bb.fitness.repository.Repository;

import rx.Observable;

/**
 * Created by sharpay on 17-3-22.
 */

public class GetBeautyList extends UseCase<GetBeautyList.RequestValues,GetBeautyList.ResponseValue> {

    private final Repository mRepository;

    public GetBeautyList(Repository repository) {
        mRepository = repository;
    }

    @Override
    public ResponseValue execute(RequestValues requestValues) {
        return new ResponseValue(mRepository.getBeautyList());
    }

    public static final class RequestValues implements UseCase.RequestValues{

        public RequestValues(){
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Observable<BeautyList> mBeautyListObservable;

        public ResponseValue(Observable<BeautyList> mBeautyListObservable) {
            this.mBeautyListObservable = mBeautyListObservable;
        }

        public Observable<BeautyList> getBeautyList(){
            return mBeautyListObservable;
        }
    }

}
