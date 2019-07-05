package com.hongen.kong.service;


import com.hongen.kong.model.*;

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



    List<KongPlugin> getPlugins(String service, String route_id);
    KongPlugin getPlugin(String plugin_id);
    void addPlugin(KongPlugin plugin);
    void updatePlugin(KongPlugin plugin);
    void deletePlugin(String plugin_id);

    List<Consumer> getConsumers();
    Consumer getConsumer(String usernameOrId);
    void addConsumer(Consumer consumer);
    void updateConsumer(Consumer consumer);
    void deleteConsumer(String usernameOrCustom_id);

    List<ConsumerJwt> getConsumerJwts(String username);
    void addConsumerJwt(String username);
    void delConsumerJwt(String username, String jwt_id);

    List<ConsumerAcl> getConsumerAcls(String username);

    List<ConsumerAcl> getAllAcls();

    List<KongUpstream> getUpstreams();
    KongUpstream getUpstream(String upstream_id);
    void addUpstream(KongUpstream upstream);
    void updateUpstream(KongUpstream upstream);
    void deleteUpstream(String upstream_id);

    List<KongTarget> getTargets(String upstream);
    void addTarget(KongTarget target);
    void deleteTarget(String upstream_id, String target_id);


}
