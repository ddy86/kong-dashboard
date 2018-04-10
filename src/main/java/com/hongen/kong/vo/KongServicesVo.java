package com.hongen.kong.vo;

import com.hongen.kong.model.KongService;

import java.util.List;

/**
 * Created by ddy on 2018/4/4.
 */
public class KongServicesVo {

    String next;
    List<KongService> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<KongService> getData() {
        return data;
    }

    public void setData(List<KongService> data) {
        this.data = data;
    }
}
