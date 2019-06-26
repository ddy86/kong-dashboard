package com.hongen.kong.model;


import java.util.List;

/**
 * Created by ddy on 2018/4/8.
 */
public class KongPluginConfig {
    //jwt
    private List<String> claims_to_verify;
    private Boolean secret_is_base64;
    private String key_claim_name;
    private Object cookie_names;
    private String anonymous;
    private Boolean run_on_preflight;
    private List<String> uri_param_names;

    //statsd
    private String host;
    private Integer port = 8125;
    //rate-limiting
    private Integer second;
    private Integer hour;
    //file-log
    private String path;

    public List<String> getClaims_to_verify() {
        return claims_to_verify;
    }

    public void setClaims_to_verify(List<String> claims_to_verify) {
        this.claims_to_verify = claims_to_verify;
    }

    public Boolean getSecret_is_base64() {
        return secret_is_base64;
    }

    public void setSecret_is_base64(Boolean secret_is_base64) {
        this.secret_is_base64 = secret_is_base64;
    }

    public String getKey_claim_name() {
        return key_claim_name;
    }

    public void setKey_claim_name(String key_claim_name) {
        this.key_claim_name = key_claim_name;
    }

    public Object getCookie_names() {
        return cookie_names;
    }

    public void setCookie_names(Object cookie_names) {
        this.cookie_names = cookie_names;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public Boolean getRun_on_preflight() {
        return run_on_preflight;
    }

    public void setRun_on_preflight(Boolean run_on_preflight) {
        this.run_on_preflight = run_on_preflight;
    }

    public List<String> getUri_param_names() {
        return uri_param_names;
    }

    public void setUri_param_names(List<String> uri_param_names) {
        this.uri_param_names = uri_param_names;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
