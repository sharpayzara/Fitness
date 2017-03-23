package com.nick.bb.fitness.api.entity.decor;

import com.nick.bb.fitness.api.entity.BeautyBean;

import java.util.List;

/**
 * Created by sharpay on 17-3-22.
 */

public class BeautyList extends BaseData{
    public List<BeautyBean> results;

    public List<BeautyBean> getResults() {
        return results;
    }

    public void setResults(List<BeautyBean> results) {
        this.results = results;
    }
}
