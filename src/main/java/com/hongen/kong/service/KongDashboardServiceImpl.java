package com.hongen.kong.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.github.kevinsawicki.http.HttpRequest;
import com.hongen.kong.model.Consumer;
import com.hongen.kong.model.KongPlugin;
import com.hongen.kong.model.KongRoute;
import com.hongen.kong.model.KongService;
import com.hongen.kong.utils.HttpRequestUtils;
import com.hongen.kong.vo.ConsumersVo;
import com.hongen.kong.vo.KongPluginsVo;
import com.hongen.kong.vo.KongRoutesVo;
import com.hongen.kong.vo.KongServicesVo;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ddy on 2018/4/4.
 */
@Service
public class KongDashboardServiceImpl implements KongDashboardService {

    protected static final Logger logger = LoggerFactory.getLogger(KongDashboardService.class);
    @Value("${kong.server.url}")
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
        return kongServicesVo.getData();
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
        Map<String,String> map = new HashMap<>();
        map.put("name",service.getName());
        map.put("url",service.getUrl());
        final HttpRequest form = HttpRequest.post(url).form(map);
        if(form.created()){
            logger.info("add service {} success!", service.getName());
        }
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



    @Override
    public void addPlugin(KongPlugin plugin) {
        String url = kongServer;
        if(!StringUtils.isEmpty(plugin.getService_id())){
            url = url + "/services/" + plugin.getService_id() + "/plugins";
        }else if(!StringUtils.isEmpty(plugin.getRoute_id())){
            url = url + "/routes/" + plugin.getRoute_id() + "/plugins";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("name",plugin.getName());
        map.put("config",plugin.getConfig());
        String json = JSON.toJSONString(map);
        logger.info("add plugin {}", json);
        final String data = HttpRequestUtils.post(url, json);
        logger.info("add plugin success! data:{}",data);
    }

    @Override
    public void updatePlugin(KongPlugin plugin) {
        String url = kongServer + "/plugins/" + plugin.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("name",plugin.getName());
        map.put("config",plugin.getConfig());
        String json = JSON.toJSONString(map);
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
}
