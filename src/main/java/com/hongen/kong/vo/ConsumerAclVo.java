package com.hongen.kong.vo;

import com.hongen.kong.model.ConsumerAcl;

import java.util.List;

/**
 * Created by ddy on 2019/6/26 5:35 PM
 */

public class ConsumerAclVo {
    private String next;
    private List<ConsumerAcl> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<ConsumerAcl> getData() {
        return data;
    }

    public void setData(List<ConsumerAcl> data) {
        this.data = data;
    }
}
