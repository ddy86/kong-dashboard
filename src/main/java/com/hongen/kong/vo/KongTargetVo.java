package com.hongen.kong.vo;

import com.hongen.kong.model.KongTarget;

import java.util.List;

/**
 * Created by ddy on 2019/7/4 3:42 PM
 */

public class KongTargetVo {
    private String next;
    private List<KongTarget> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<KongTarget> getData() {
        return data;
    }

    public void setData(List<KongTarget> data) {
        this.data = data;
    }
}
