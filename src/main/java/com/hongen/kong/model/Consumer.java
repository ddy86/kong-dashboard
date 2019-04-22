package com.hongen.kong.model;

/**
 * Created by ddy on 2018/4/8.
 */
public class Consumer {

    String custom_id;
    long created_at;
    String username;
    String id;

    public Consumer(String consumer_id) {
        id = consumer_id;
    }
    public Consumer() {}

    public String getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
