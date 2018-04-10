package com.hongen.kong.vo;

import com.hongen.kong.model.KongPlugin;

import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class KongPluginsVo {
    int total;
    List<KongPlugin> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<KongPlugin> getData() {
        return data;
    }

    public void setData(List<KongPlugin> data) {
        this.data = data;
    }
}
