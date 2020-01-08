package com.hongen.kong.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by ddy on 2018/5/30.
 */

@Controller
@RequestMapping()
public class DefaultController {

    @Value("${KONG_ENVIRONMENT_TYPE}")
    String environmentType;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        String title = "${KONG_ENVIRONMENT_TYPE}".equals(environmentType) ? "" : environmentType + "-";
        model.addAttribute("title", title);
    }
}
