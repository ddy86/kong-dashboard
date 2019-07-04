package com.hongen.kong.model;

/**
 * Created by ddy on 2019/7/4 3:38 PM
 */

public class KongTarget {
    private String id;
    private String target;
    private int weight;
    private long created_at;
    private KongUpstream upstream;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public KongUpstream getUpstream() {
        return upstream;
    }

    public void setUpstream(KongUpstream upstream) {
        this.upstream = upstream;
    }
}
