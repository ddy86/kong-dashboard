package com.hongen.kong.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.github.kevinsawicki.http.HttpRequest;
import com.hongen.kong.model.*;
import com.hongen.kong.utils.HttpRequestUtils;
import com.hongen.kong.vo.*;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by ddy on 2018/4/4.
 */
@Service
public class KongDashboardServiceImpl implements KongDashboardService {

    protected static final Logger logger = LoggerFactory.getLogger(KongDashboardService.class);
    @Value("${KONG_API_URL}")
    String kongServer;

    @Override
    public List<KongService> getServices() {
        String url = kongServer + "/services";
        HttpRequest result =  HttpRequest.get(url);
        if(result.code() != 200){
           return null;
        }
        String servicesData = result.body();
        final KongServicesVo kongServicesVo = JSON.parseObject(servicesData, new TypeReference<KongServicesVo>() {
        });
        List<KongService> services = kongServicesVo.getData();
        services.sort(Comparator.comparing(KongService::getName));
        return services;
    }

    @Override
    public KongService getService(String service,String route_id) {
        String url = kongServer;
        if(null == route_id){
            url = url + "/services/" + service;
        }else{
            url = url + "/routes/" + route_id + "/service";
        }


        HttpRequest result =  HttpRequest.get(url);
        if(result.code() != 200){
            return null;
        }
        String serviceData = result.body();
        final KongService kongService = JSON.parseObject(serviceData, new TypeReference<KongService>() {});
        return kongService;
    }
    @Override
    public void addService(KongService service) {
        String url = kongServer + "/services";
        Map<String,Object> map = new HashMap<>();
        map.put("name",service.getName());
        map.put("url",service.getUrl());
        try {
            HttpClientUtil.post(HttpConfig.custom().url(url).map(map));
        } catch (HttpProcessException e) {
            logger.error("add service {} failed:{}!", service.getName(),e.getMessage());
            e.printStackTrace();
        }
        logger.info("add service {} success!", service.getName());
    }

    @Override
    public void updateService(KongService service) {
        String url = kongServer + "/services/" + service.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("name",service.getName());
        map.put("url",service.getUrl());
        try {
            HttpClientUtil.patch(
                    HttpConfig.custom().url(url).map(map)
            );
        } catch (HttpProcessException e) {
            e.printStackTrace();
        logger.error("update service {}, url:{} error: {}",service.getName(),url,e.getMessage());
        }
        logger.info("update service {} success!", service.getName());
    }

    @Override
    public void deleteService(String service_id) {
        String url = kongServer + "/services/" + service_id;
        final String data = HttpRequestUtils.delete(url);
        logger.info("delete service {} result: {}",service_id,data);
    }

    @Override
    public List<KongRoute> getRoutes(String service) {
        String url = kongServer + "/routes";
        if(null != service){
            url = kongServer + "/services/" + service  + "/routes";
        }

        HttpRequest result =  HttpRequest.get(url);
        if(result.code() != 200){
            return null;
        }
        String routesData = result.body();
        final KongRoutesVo kongRoutesVo = JSON.parseObject(routesData, new TypeReference<KongRoutesVo>() {
        });
        return kongRoutesVo.getData();
    }

    @Override
    public KongRoute getRoute(String route_id) {
        String url = kongServer + "/routes/" + route_id;
        HttpRequest result =  HttpRequest.get(url);
        if(result.code() != 200){
            return null;
        }
        String data = result.body();
        final KongRoute kongRoute = JSON.parseObject(data, new TypeReference<KongRoute>() {
        });
        return kongRoute;
    }

    @Override
    public void addRoute(KongRoute route) {
        String url = kongServer + "/services/" + route.getService().getId() + "/routes";
        Map<String,Object> map = new HashMap<>();
        map.put("hosts",route.getHosts());
        map.put("paths",route.getPaths());
        map.put("protocols",route.getProtocols());
        map.put("methods",route.getMethods());
        map.put("strip_path",route.isStrip_path());
        try {
            final String patch = HttpClientUtil.post(
                    HttpConfig.custom().url(url).json(JSON.toJSONString(map))
                            .headers(new Header[]{new BasicHeader("Content-Type", "application/json")})
            );
            logger.info("add route {}:{} success! result:{}", route.getHosts(),route.getPaths(),patch);
        } catch (HttpProcessException e) {
            e.printStackTrace();
            logger.error("add route {}:{}, url:{} error: {}",route.getHosts(),route.getPaths(),url,e.getMessage());
        }
    }

    @Override
    public void updateRoute(KongRoute route) {
        String url = kongServer + "/routes/" + route.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("hosts",route.getHosts());
        map.put("paths",route.getPaths());
        map.put("protocols",route.getProtocols());
        map.put("methods",route.getMethods());
        map.put("strip_path",route.isStrip_path());
        logger.info("update route {}", JSON.toJSONString(map));

        try {
            final String patch = HttpClientUtil.patch(
                    HttpConfig.custom().url(url).json(JSON.toJSONString(map))
                            .headers(new Header[]{new BasicHeader("Content-Type", "application/json")})
            );
            logger.info("update route {}:{} success! result:{}", route.getHosts(),route.getPaths(),patch);
        } catch (HttpProcessException e) {
            e.printStackTrace();
            logger.error("update route {}:{}, url:{} error: {}",route.getHosts(),route.getPaths(),url,e.getMessage());
        }
    }

    @Override
    public void deleteRoute(String route_id) {
        String url = kongServer + "/routes/" + route_id;
        final HttpRequest delete = HttpRequest.delete(url);
        logger.info("delete route {} result: {}",route_id,delete.ok());
    }



    @Override
    public List<KongPlugin> getPlugins(String service, String route_id) {
        String url = kongServer;
        if(null != service){
            url = url + "/services/" + service + "/plugins";
        }else if(null != route_id){
            url = url + "/routes/" + route_id + "/plugins";
        }else{
            url = url + "/plugins";
        }


        HttpRequest result =  HttpRequest.get(url);
        if(result.code() != 200){
            return null;
        }
        String data = result.body();
        final KongPluginsVo pluginsVo = JSON.parseObject(data, new TypeReference<KongPluginsVo>() {});
        return pluginsVo.getData();
    }

    @Override
    public KongPlugin getPlugin(String plugin_id) {
        String url = kongServer + "/plugins/" + plugin_id;
        final String data = HttpRequestUtils.get(url);
        logger.info("get Plugin : {}",data);
        final KongPlugin plugin = JSON.parseObject(data, new TypeReference<KongPlugin>() {
        });
        return plugin;
    }


    class KongPluginService{

        String id;
        public KongPluginService(String service_id){
            id = service_id;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    class KongPluginRoute{
        String id;
        public KongPluginRoute(String route_id){
            id = route_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
    class KongPluginConsumer{
        String id;
        KongPluginConsumer(String consumer_id){
            id = consumer_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    class KongPluginJwtConfig{
        List<String> claims_to_verify;

        public List<String> getClaims_to_verify() {
            return claims_to_verify;
        }

        public void setClaims_to_verify(List<String> claims_to_verify) {
            this.claims_to_verify = claims_to_verify;
        }

    }

    class KongPluginObject{
        String id;
        String name;
        Object config;
        KongPluginConsumer consumer;
        KongPluginRoute route;
        KongPluginService service;

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

        public Object getConfig() {
            return config;
        }

        public void setConfig(Object config) {
            this.config = config;
        }

        public KongPluginConsumer getConsumer() {
            return consumer;
        }

        public void setConsumer(KongPluginConsumer consumer) {
            this.consumer = consumer;
        }

        public KongPluginRoute getRoute() {
            return route;
        }

        public void setRoute(KongPluginRoute route) {
            this.route = route;
        }

        public KongPluginService getService() {
            return service;
        }

        public void setService(KongPluginService service) {
            this.service = service;
        }
    }


    @Override
    public void addPlugin(KongPlugin plugin) {
        String url = kongServer;
        if(!StringUtils.isEmpty(plugin.getService().getId())){
            url = url + "/services/" + plugin.getService().getId() + "/plugins";
        }else if(!StringUtils.isEmpty(plugin.getRoute().getId())){
            url = url + "/routes/" + plugin.getRoute().getId() + "/plugins";
        }else{
            url = url + "/plugins";
        }
        Map<String,Object> map = new HashMap<>();
        KongPluginObject kongPluginObject = new KongPluginObject();
        kongPluginObject.setName(plugin.getName());
        switch (plugin.getName()){
            case "jwt":
                if(plugin.getConfig().getClaims_to_verify().size() > 0){
                    KongPluginJwtConfig jwt_config = new KongPluginJwtConfig();
                    jwt_config.setClaims_to_verify(plugin.getConfig().getClaims_to_verify());
                    kongPluginObject.setConfig(jwt_config);
                }
                break;
            case "statsd":
                map.put("config.host",plugin.getConfig().getHost());
                map.put("config.port",plugin.getConfig().getPort());
                break;
            case "rate-limiting":
                map.put("config.second",plugin.getConfig().getSecond());
                map.put("config.hour",plugin.getConfig().getHour());
                break;
            case "file-log":
                map.put("config.path",plugin.getConfig().getPath());
                break;
        }
        if(!StringUtils.isEmpty(plugin.getRoute().getId())){
            kongPluginObject.setRoute(new KongPluginRoute(plugin.getRoute().getId()));
        }
        if(!StringUtils.isEmpty(plugin.getService().getId())){
            kongPluginObject.setService(new KongPluginService(plugin.getService().getId()));
        }
        if(!StringUtils.isEmpty(plugin.getConsumer().getId())){
            kongPluginObject.setConsumer(new KongPluginConsumer(plugin.getConsumer().getId()));
        }
        String json = JSON.toJSONString(kongPluginObject);
        logger.info("add plugin {}", json);
        final String data = HttpRequestUtils.post(url, json);
        logger.info("add plugin success! data:{}",data);
    }

    @Override
    public void updatePlugin(KongPlugin plugin) {
        String url = kongServer + "/plugins/" + plugin.getId();
//        Map<String,Object> map = new HashMap<>();
//        map.put("name",plugin.getName());
//        map.put("config",plugin.getConfig());

        String json = JSON.toJSONString(plugin);
        logger.info("update plugin {}", json);
        final String data = HttpRequestUtils.patch(url, json);
        logger.info("update plugin success! data:{}",data);
    }

    @Override
    public void deletePlugin(String plugin_id) {
        String url = kongServer + "/plugins/" + plugin_id;
        final String data = HttpRequestUtils.delete(url);
        logger.info("delete plugin success! data:{}",data);
    }
    @Override
    public List<Consumer> getConsumers() {
        String url = kongServer + "/consumers";
        HttpRequest result =  HttpRequest.get(url);
        if(result.code() != 200){
            return null;
        }
        String data = result.body();
        final ConsumersVo consumersVo = JSON.parseObject(data, new TypeReference<ConsumersVo>() {});
        return consumersVo.getData();
    }

    @Override
    public Consumer getConsumer(String usernameOrId) {
        String url = kongServer + "/consumers/" + usernameOrId;
        final String data = HttpRequestUtils.get(url);
        final Consumer consumer = JSON.parseObject(data, new TypeReference<Consumer>() {
        });
        return consumer;
    }

    @Override
    public void addConsumer(Consumer consumer) {
        String url = kongServer + "/consumers";
        Map<String,Object> map = new HashMap<>();
        map.put("username",consumer.getUsername());
        map.put("custom_id",consumer.getCustom_id());
        String json = JSON.toJSONString(map);
        logger.info("add consumer {}", json);
        final String data = HttpRequestUtils.post(url, json);
        logger.info("add consumer success! data:{}",data);
    }

    @Override
    public void updateConsumer(Consumer consumer) {
        String url = kongServer + "/consumers/" + consumer.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("username",consumer.getUsername());
        map.put("custom_id",consumer.getCustom_id());
        String json = JSON.toJSONString(map);
        logger.info("update consumer {}", json);
        final String data = HttpRequestUtils.patch(url, json);
        logger.info("update consumer success! data:{}",data);
    }

    @Override
    public void deleteConsumer(String usernameOrCustom_id) {
        String url = kongServer + "/consumers/" + usernameOrCustom_id;
        final String data = HttpRequestUtils.delete(url);
        logger.info("delete consumer success! data:{}",data);
    }

    @Override
    public List<ConsumerJwt> getConsumerJwts(String username) {
        String url = kongServer + "/consumers/" + username + "/jwt";
        final String data = HttpRequestUtils.get(url);
        final ConsumerJwtVo consumerJwtVo = JSON.parseObject(data, new TypeReference<ConsumerJwtVo>() {
        });
        return consumerJwtVo.getData();
    }

    @Override
    public void addConsumerJwt(String username) {
        String url = kongServer + "/consumers/" + username + "/jwt";
        final String data = HttpRequestUtils.post(url);
        logger.info("add consumer jwt success! data:{}",data);
    }

    @Override
    public void delConsumerJwt(String username, String jwt_id) {
        String url = kongServer + "/consumers/" + username + "/jwt/" + jwt_id;
        final String data = HttpRequestUtils.delete(url);
        logger.info("delete consumer jwt success! data:{}",data);
    }
}
