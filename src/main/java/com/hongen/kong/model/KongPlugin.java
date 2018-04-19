package com.hongen.kong.model;

/**
 * Created by ddy on 2018/4/8.
 */
public class KongPlugin {

    String id;
    String name;
    String service_id;
    String route_id;
    Boolean enabled;
    KongPluginConfig config;
    Long created_at;
    String consumer_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public KongPluginConfig getConfig() {
        return config;
    }

    public void setConfig(KongPluginConfig config) {
        this.config = config;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }
}
