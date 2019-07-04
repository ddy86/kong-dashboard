package com.hongen.kong.model;

/**
 * Created by ddy on 2019/7/4 11:35 AM
 */

public class KongUpstream {
    private String id;
    private long created_at;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
