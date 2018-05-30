package com.hongen.kong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by ddy on 2018/5/30.
 */

@Controller
@RequestMapping()
public class DefaultController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getServiceList(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
