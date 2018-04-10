package com.hongen.kong.service;


import com.hongen.kong.model.Consumer;
import com.hongen.kong.model.KongPlugin;
import com.hongen.kong.model.KongRoute;
import com.hongen.kong.model.KongService;

import java.util.List;

/**
 * Created by ddy on 2018/4/4.
 */
public interface KongDashboardService {

    List<KongService> getServices();
    KongService getService(String service,String route_id);
    void addService(KongService service);
    void updateService(KongService service);
    void deleteService(String service_id);

    List<KongRoute> getRoutes(String service);
    KongRoute getRoute(String route_id);
    void addRoute(KongRoute route);
    void updateRoute(KongRoute route);
    void deleteRoute(String route_id);


    List<Consumer> getConsumers();

    List<KongPlugin> getPlugins(String service, String route_id);
    KongPlugin getPlugin(String plugin_id);
    void addPlugin(KongPlugin plugin);
    void updatePlugin(KongPlugin plugin);
    void deletePlugin(String plugin_id);

}
