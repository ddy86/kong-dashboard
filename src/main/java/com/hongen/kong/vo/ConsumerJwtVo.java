package com.hongen.kong.vo;

import com.hongen.kong.model.ConsumerJwt;

import java.util.List;

/**
 * Created by ddy on 2018/4/10.
 */
public class ConsumerJwtVo {
    int total;
    List<ConsumerJwt> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ConsumerJwt> getData() {
        return data;
    }

    public void setData(List<ConsumerJwt> data) {
        this.data = data;
    }
}
