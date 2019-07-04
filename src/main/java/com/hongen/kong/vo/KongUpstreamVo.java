package com.hongen.kong.vo;

import com.hongen.kong.model.KongUpstream;

import java.util.List;

/**
 * Created by ddy on 2019/7/4 11:38 AM
 */

public class KongUpstreamVo {
    private String next;
    private List<KongUpstream> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<KongUpstream> getData() {
        return data;
    }

    public void setData(List<KongUpstream> data) {
        this.data = data;
    }
}
