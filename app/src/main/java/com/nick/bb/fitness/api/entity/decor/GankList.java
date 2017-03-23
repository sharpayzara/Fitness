package com.nick.bb.fitness.api.entity.decor;

import com.nick.bb.fitness.api.entity.GankBean;

import java.util.List;

/**
 * Created by sharpay on 17-3-22.
 */

public class GankList extends BaseData{
    public List<GankBean> results;

    public List<GankBean> getResults() {
        return results;
    }

    public void setResults(List<GankBean> results) {
        this.results = results;
    }
}
