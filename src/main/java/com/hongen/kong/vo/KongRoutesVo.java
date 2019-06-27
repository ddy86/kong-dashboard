package com.hongen.kong.vo;

import com.hongen.kong.model.KongRoute;

import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class KongRoutesVo {

    private String next;
    private List<KongRoute> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<KongRoute> getData() {
        return data;
    }

    public void setData(List<KongRoute> data) {
        this.data = data;
    }
}
