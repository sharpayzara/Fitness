package com.nick.bb.fitness.ui.adapter;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by sharpay on 17-3-31.
 */


public class DiffCallBack <E> extends DiffUtil.Callback {
    private List<E> mOldDatas, mNewDatas;

    public DiffCallBack(List<E> mOldDatas, List<E> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        E beanOld = mOldDatas.get(oldItemPosition);
        E beanNew = mNewDatas.get(newItemPosition);
        return  true;
    }
}
