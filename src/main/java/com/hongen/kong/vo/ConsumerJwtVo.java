package com.hongen.kong.vo;

import com.hongen.kong.model.ConsumerJwt;

import java.util.List;

/**
 * Created by ddy on 2018/4/10.
 */
public class ConsumerJwtVo {
    private String next;
    private List<ConsumerJwt> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<ConsumerJwt> getData() {
        return data;
    }

    public void setData(List<ConsumerJwt> data) {
        this.data = data;
    }
}
