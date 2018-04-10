package com.hongen.kong.model;

import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class KongRoute {
    long created_at;
    boolean strip_path;
    List<String> hosts;
    boolean preserve_host;
    int regex_priority;         //优先级
    long updated_at;
    List<String> paths;
    KongService service;
    List<String> methods;
    List<String> protocols;
    String id;

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public boolean isStrip_path() {
        return strip_path;
    }

    public void setStrip_path(boolean strip_path) {
        this.strip_path = strip_path;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public boolean isPreserve_host() {
        return preserve_host;
    }

    public void setPreserve_host(boolean preserve_host) {
        this.preserve_host = preserve_host;
    }

    public int getRegex_priority() {
        return regex_priority;
    }

    public void setRegex_priority(int regex_priority) {
        this.regex_priority = regex_priority;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public KongService getService() {
        return service;
    }

    public void setService(KongService service) {
        this.service = service;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public List<String> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<String> protocols) {
        this.protocols = protocols;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
