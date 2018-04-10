package com.hongen.kong.vo;

import com.hongen.kong.model.Consumer;

import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class ConsumersVo {
    int total;
    List<Consumer> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Consumer> getData() {
        return data;
    }

    public void setData(List<Consumer> data) {
        this.data = data;
    }
}
