package com.hongen.kong.model;

/**
 * Created by ddy on 2018/4/4.
 */
public class KongService {

    String host;	        //The host of the upstream server.
    long created_at;
    long connect_timeout;   //optional	//The timeout in milliseconds for establishing a connection to the upstream server. Defaults to 60000.
    String id;
    String protocol;	    //The protocol used to communicate with the upstream. It can be one of http (default) or https.
    String name;	        //The KongDashboardService name.
    long read_timeout;      //optional	//The timeout in milliseconds between two successive read operations for transmitting a request to the upstream server. Defaults to 60000.
    int port;	            //The upstream server port. Defaults to 80.
    String path;            //optional	//The path to be used in requests to the upstream server. Empty by default.
    int retries;            //optional	//The number of retries to execute upon failure to proxy. The default is 5.
    long updated_at;
    long write_timeout;     //optional	//The timeout in milliseconds between two successive write operations for transmitting a request to the upstream server. Defaults to 60000.

    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getConnect_timeout() {
        return connect_timeout;
    }

    public void setConnect_timeout(long connect_timeout) {
        this.connect_timeout = connect_timeout;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRead_timeout() {
        return read_timeout;
    }

    public void setRead_timeout(long read_timeout) {
        this.read_timeout = read_timeout;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public long getWrite_timeout() {
        return write_timeout;
    }

    public void setWrite_timeout(long write_timeout) {
        this.write_timeout = write_timeout;
    }
}
