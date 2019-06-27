package com.hongen.kong.vo;

import com.hongen.kong.model.Consumer;

import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class ConsumersVo {
    private String next;
    private List<Consumer> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Consumer> getData() {
        return data;
    }

    public void setData(List<Consumer> data) {
        this.data = data;
    }
}
