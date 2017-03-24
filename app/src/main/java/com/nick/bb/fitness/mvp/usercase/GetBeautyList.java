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
        return new ResponseValue(mRepository.getBeautyList(requestValues.getPage(),requestValues.getSize()));
    }

    public static final class RequestValues implements UseCase.RequestValues{
        int page,size;

        public RequestValues(int page, int size) {
            this.page = page;
            this.size = size;
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

        private final Observable<BeautyList> mBeautyListObservable;

        public ResponseValue(Observable<BeautyList> mBeautyListObservable) {
            this.mBeautyListObservable = mBeautyListObservable;
        }

        public Observable<BeautyList> getBeautyList(){
            return mBeautyListObservable;
        }
    }

}
