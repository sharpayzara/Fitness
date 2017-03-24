package com.nick.bb.fitness.mvp.usercase;


import com.nick.bb.fitness.api.entity.decor.GankList;
import com.nick.bb.fitness.mvp.usercase.base.UseCase;
import com.nick.bb.fitness.repository.Repository;

import rx.Observable;

/**
 * Created by sharpay on 17-3-22.
 */

public class GetGankList extends UseCase<GetGankList.RequestValues,GetGankList.ResponseValue> {

    private final Repository mRepository;

    public GetGankList(Repository repository) {
        mRepository = repository;
    }

    @Override
    public ResponseValue execute(RequestValues requestValues) {
        return new ResponseValue(mRepository.getGankList(requestValues.getPage(),requestValues.getSize(),requestValues.type));
    }

    public static final class RequestValues implements UseCase.RequestValues{
        int page,size;
        String type;

        public RequestValues(int page, int size, String type) {
            this.page = page;
            this.size = size;
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Observable<GankList> mGankListObservable;

        public ResponseValue(Observable<GankList> mGankListObservable) {
            this.mGankListObservable = mGankListObservable;
        }

        public Observable<GankList> getGankList(){
            return mGankListObservable;
        }
    }

}
