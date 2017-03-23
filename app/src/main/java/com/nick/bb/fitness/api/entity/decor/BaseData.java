package com.nick.bb.fitness.api.entity.decor;

import java.io.Serializable;

/**
 * Created by sharpay on 17-3-22.
 */
public class BaseData implements Serializable{
    public boolean error;

    @Override
    public String toString() {
        return "BaseData{" +
                "error=" + error +
                '}';
    }
}
