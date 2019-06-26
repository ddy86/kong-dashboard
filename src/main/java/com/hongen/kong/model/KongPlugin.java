package com.hongen.kong.model;

import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class KongPlugin {

    private String id;
    private String name;
    private KongService service;
    private KongRoute route;
    private Boolean enabled;
    private KongPluginConfig config;
    private Long created_at;
    private Consumer consumer;
    private String run_on;
    private List<String> protocols;
    private List<String> tags;

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

    public KongService getService() {
        return service;
    }

    public void setService(KongService service) {
        this.service = service;
    }

    public KongRoute getRoute() {
        return route;
    }

    public void setRoute(KongRoute route) {
        this.route = route;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public String getRun_on() {
        return run_on;
    }

    public void setRun_on(String run_on) {
        this.run_on = run_on;
    }

    public List<String> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<String> protocols) {
        this.protocols = protocols;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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


}
