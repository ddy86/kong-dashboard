package com.hongen.kong.web;

import com.alibaba.fastjson.JSON;
import com.hongen.kong.model.*;
import com.hongen.kong.service.KongDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ddy on 2018/4/4.
 */

@Controller
@RequestMapping(value = "/kong")
public class KongDashboardController {

    @Autowired
    KongDashboardService kongDashboardService;

    @RequestMapping(value = "/services",method = RequestMethod.GET)
    public String getServiceList(ModelMap map){
        final List<KongService> services = kongDashboardService.getServices();
        map.addAttribute("services", services);
        map.addAttribute("nav_service", true);
        return "services";
    }

    @ResponseBody
    @RequestMapping(value = "/all_services",method = RequestMethod.GET)
    public List<KongService> getAllServices(){
        return kongDashboardService.getServices();
    }

    @RequestMapping(value = "/services/add",method = RequestMethod.GET)
    public String addService(ModelMap map){
        KongService service = new KongService();
        map.addAttribute("service",service);
        map.addAttribute("action","add");
        map.addAttribute("nav_service", true);
        return "serviceForm";
    }

    @ResponseBody
    @RequestMapping(value = "/services/{service_id}/obj",method = RequestMethod.GET)
    public KongService getServiceObj(@PathVariable String service_id){
        KongService service = kongDashboardService.getService(service_id,null);
        return service;
    }
    @ResponseBody
    @RequestMapping(value = "/routes/{route_id}/service/obj",method = RequestMethod.GET)
    public KongService getServiceObjByRoute(@PathVariable String route_id){
        KongService service = kongDashboardService.getService(null,route_id);
        return service;
    }

    @RequestMapping(value = "/services/{service_id}",method = RequestMethod.GET)
    public String updateService(ModelMap map, @PathVariable String service_id){
        KongService service = kongDashboardService.getService(service_id,null);
        service = setServiceUrl(service);
        map.addAttribute("service",service);
        map.addAttribute("action","update");
        map.addAttribute("nav_service", true);
        return "serviceForm";
    }

    public KongService setServiceUrl(KongService service){
        String url = service.getProtocol() + "://" + service.getHost();
        if(service.getPort() != 80){
            url += ":" + service.getPort();
        }
        if(null != service.getPath()){
            url += service.getPath();
        }
        service.setUrl(url);
        return service;
    }

    @RequestMapping(value = "/services/save", method = RequestMethod.POST)
    public String saveService(@ModelAttribute KongService service) {
        if(StringUtils.isEmpty(service.getId())){
            kongDashboardService.addService(service);
        }else{
            kongDashboardService.updateService(service);
        }

        return "redirect:/kong/services/";
    }

    @ResponseBody
    @RequestMapping(value = "/services/{service_id}/del",method = RequestMethod.DELETE)
    public void delService(@PathVariable String service_id){
        kongDashboardService.deleteService(service_id);
    }

    @RequestMapping(value = "/routes/{route_id}",method = RequestMethod.GET)
    public String updateRoute(ModelMap map, @PathVariable String route_id){
        final KongRoute route = kongDashboardService.getRoute(route_id);
        map.addAttribute("route", route);
        KongService service = kongDashboardService.getService(null,route_id);
        service = setServiceUrl(service);
        map.addAttribute("service",service);
        map.addAttribute("action", "update");
        map.addAttribute("nav_route", true);
        return "routeForm";
    }

    @ResponseBody
    @RequestMapping(value = "/routes/{route_id}/obj",method = RequestMethod.GET)
    public KongRoute getRouteObj(@PathVariable String route_id){
        KongRoute route = kongDashboardService.getRoute(route_id);
        return route;
    }


    @RequestMapping(value = "/routes/{service_id}/add",method = RequestMethod.GET)
    public String addRoute(ModelMap map, @PathVariable String service_id){
        KongRoute route = new KongRoute();
        map.addAttribute("route", route);
        KongService service = kongDashboardService.getService(service_id,null);
        service = setServiceUrl(service);
        map.addAttribute("service",service);
        map.addAttribute("action", "add");
        map.addAttribute("nav_route", true);
        return "routeForm";
    }

    @ResponseBody
    @RequestMapping(value = "/routes/{route_id}/del",method = RequestMethod.DELETE)
    public void delRoute(@PathVariable String route_id){
        kongDashboardService.deleteRoute(route_id);
    }

    @RequestMapping(value = "/routes",method = RequestMethod.GET)
    public String getRoutesList(ModelMap map, @RequestParam(value = "service",required = false) String service){
        final List<KongRoute> routes = kongDashboardService.getRoutes(service);
        if(null != service){
            KongService kongService = kongDashboardService.getService(service,null);
            kongService = setServiceUrl(kongService);
            map.addAttribute("service", kongService);
        }
        map.addAttribute("routes", routes);
        map.addAttribute("nav_route", true);
        return "routes";
    }

    @ResponseBody
    @RequestMapping(value = "/all_routes",method = RequestMethod.GET)
    public List<KongRoute> getAllRoutes(){
        return kongDashboardService.getRoutes(null);
    }

    @RequestMapping(value = "/routes/save", method = RequestMethod.POST)
    public String saveRoute(@ModelAttribute KongRoute route) {
        if(StringUtils.isEmpty(route.getId())){
            kongDashboardService.addRoute(route);
        }else{
            kongDashboardService.updateRoute(route);
        }

        return "redirect:/kong/routes?service="+route.getService().getId();
    }


    @RequestMapping(value = "/plugins",method = RequestMethod.GET)
    public String getPluginsList(ModelMap map,@RequestParam(value = "service",required = false) String service,
                                 @RequestParam(value = "route_id",required = false) String route_id){
        final List<KongPlugin> plugins = kongDashboardService.getPlugins(service,route_id);
        if(null != service){
            KongService kongService = kongDashboardService.getService(service,null);
            map.addAttribute("serviceName", kongService.getName());
            map.addAttribute("service_id", kongService.getId());
        }else if(null != route_id){
            KongService kongService = kongDashboardService.getService(null,route_id);
            map.addAttribute("serviceName", kongService.getName());
            map.addAttribute("route_id", route_id);
            final KongRoute route = kongDashboardService.getRoute(route_id);
            String routeInfo = route.getHosts() + ":" + route.getPaths();
            map.addAttribute("routeInfo",routeInfo);
        }
        final List<Consumer> consumers = kongDashboardService.getConsumers();
        Map<String, String> consumerNameMap = new HashMap<>();
        for(Consumer consumer : consumers){
            consumerNameMap.put(consumer.getId(), consumer.getUsername());
        }
        map.addAttribute("consumerNameMap", consumerNameMap);
        map.addAttribute("plugins", plugins);
        map.addAttribute("nav_plugin", true);
        return "plugins";
    }

    @RequestMapping(value = "/plugins/{plugin_id}",method = RequestMethod.GET)
    public String updatePlugin(ModelMap map,@PathVariable(value = "plugin_id",required = true) String plugin_id){
        KongPlugin plugin = kongDashboardService.getPlugin(plugin_id);
        if(!StringUtils.isEmpty(plugin.getService())){

        }
        if(!StringUtils.isEmpty(plugin.getRoute())){

        }
        final List<Consumer> consumers = kongDashboardService.getConsumers();
        map.addAttribute("consumers", consumers);
        map.addAttribute("pluginConfig", JSON.toJSONString(plugin.getConfig()));
        map.addAttribute("plugin",plugin);
        map.addAttribute("action","update");
        map.addAttribute("nav_plugin", true);
        return "pluginForm";
    }

    @RequestMapping(value = "/plugins/add",method = RequestMethod.GET)
    public String addPlugin(ModelMap map,@RequestParam(value = "service_id",required = false) String service_id,
                                 @RequestParam(value = "route_id",required = false) String route_id){
        KongPlugin plugin = new KongPlugin();
        final KongPluginConfig pluginConfig = new KongPluginConfig();
        plugin.setConfig(pluginConfig);
        plugin.setService(new KongService(service_id));
        plugin.setRoute(new KongRoute(route_id));
        final List<Consumer> consumers = kongDashboardService.getConsumers();
        map.addAttribute("action","add");
        map.addAttribute("consumers",consumers);
        map.addAttribute("plugin",plugin);
        map.addAttribute("nav_plugin", true);
        return "pluginForm";
    }

    @RequestMapping(value = "/plugins/save",method = RequestMethod.POST)
    public String savePlugin(@ModelAttribute KongPlugin plugin){
        if(StringUtils.isEmpty(plugin.getId())){
            kongDashboardService.addPlugin(plugin);
        }else{
            kongDashboardService.updatePlugin(plugin);
        }
        if(!(null == plugin.getService()) && !StringUtils.isEmpty(plugin.getService().getId())){
            return "redirect:/kong/plugins?service=" + plugin.getService().getId();
        }else if(!(null == plugin.getRoute()) && !StringUtils.isEmpty(plugin.getRoute().getId())){
            return "redirect:/kong/plugins?route_id=" + plugin.getRoute().getId();
        }else{
            return "redirect:/kong/plugins";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/plugins/{plugin_id}/del",method = RequestMethod.DELETE)
    public void delPlugin(@PathVariable String plugin_id){
        kongDashboardService.deletePlugin(plugin_id);
    }


    @RequestMapping(value = "/consumers",method = RequestMethod.GET)
    public String getConsumersList(ModelMap map){
        final List<Consumer> consumers = kongDashboardService.getConsumers();
        map.addAttribute("consumers", consumers);
        map.addAttribute("nav_consumer", true);
        return "consumers";
    }

    @RequestMapping(value = "/consumers/add",method = RequestMethod.GET)
    public String addConsumer(ModelMap map){
        Consumer consumer = new Consumer();
        map.addAttribute("consumer",consumer);
        map.addAttribute("action","add");
        map.addAttribute("nav_consumer", true);
        return "consumerForm";
    }

    @RequestMapping(value = "/consumers/{usernameOrId}",method = RequestMethod.GET)
    public String updateConsumer(ModelMap map, @PathVariable String usernameOrId){
        final Consumer consumer = kongDashboardService.getConsumer(usernameOrId);
        map.addAttribute("consumer", consumer);
        map.addAttribute("action", "update");
        map.addAttribute("nav_consumer", true);
        return "consumerForm";
    }

    @RequestMapping(value = "/consumers/save", method = RequestMethod.POST)
    public String saveConsumer(@ModelAttribute Consumer consumer) {
        if(StringUtils.isEmpty(consumer.getId())){
            kongDashboardService.addConsumer(consumer);
        }else{
            kongDashboardService.updateConsumer(consumer);
        }
        return "redirect:/kong/consumers/";
    }

    @ResponseBody
    @RequestMapping(value = "/consumers/{usernameOrId}/del",method = RequestMethod.DELETE)
    public void delConsumer(@PathVariable String usernameOrId){
        kongDashboardService.deleteConsumer(usernameOrId);
    }

    @RequestMapping(value = "/consumers/{username}/jwt",method = RequestMethod.GET)
    public String getConsumerJWT(ModelMap map, @PathVariable String username){
        final List<ConsumerJwt> jwts = kongDashboardService.getConsumerJwts(username);
        final Consumer consumer = kongDashboardService.getConsumer(username);
        map.addAttribute("consumer", consumer);
        map.addAttribute("jwts", jwts);
        map.addAttribute("nav_consumer", true);
        return "consumerJwts";
    }

    @ResponseBody
    @RequestMapping(value = "/consumers/{username}/jwt",method = RequestMethod.POST)
    public void addConsumerJWT(@PathVariable String username){
        kongDashboardService.addConsumerJwt(username);
    }

    @ResponseBody
    @RequestMapping(value = "/consumers/{username}/jwt/{jwt_id}",method = RequestMethod.DELETE)
    public void delConsumerJWT(@PathVariable String username,@PathVariable String jwt_id){
        kongDashboardService.delConsumerJwt(username,jwt_id);
    }

    @ResponseBody
    @RequestMapping(value = "/all_acls",method = RequestMethod.GET)
    public List<ConsumerAcl> getAllACLs(){
        return kongDashboardService.getAllAcls();
    }

    @ResponseBody
    @RequestMapping(value = "/consumers/{username}/acls",method = RequestMethod.GET)
    public List<ConsumerAcl> getConsumerACLs(@PathVariable String username){
        return kongDashboardService.getConsumerAcls(username);
    }

}
