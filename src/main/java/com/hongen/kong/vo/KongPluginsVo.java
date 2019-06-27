package com.hongen.kong.vo;

import com.hongen.kong.model.KongPlugin;

import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class KongPluginsVo {
    private String next;
    private List<KongPlugin> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<KongPlugin> getData() {
        return data;
    }

    public void setData(List<KongPlugin> data) {
        this.data = data;
    }
}
