package com.nick.bb.fitness.mvp.usercase.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharpay on 17-3-22.
 */

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public abstract P execute(Q requestValues);

    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {
    }

    public class CommonRequestValue implements UseCase.RequestValues{
        public List params;
        public CommonRequestValue (Object... objects){
            if(params != null){
                params = new ArrayList();
                for(int i = 0; i < objects.length; i++){
                    params.add(objects[i]);
                }
            }
        }
        public Object get(int index){
            return params.get(index);
        }
    }
}
