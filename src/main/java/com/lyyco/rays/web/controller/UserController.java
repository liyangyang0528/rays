package com.lyyco.rays.web.controller;

import com.lyyco.rays.web.config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lyy on 2017/12/15.
 */
@RestController
@RequestMapping("/rays")
public class UserController {

    @Autowired
    Environment env;
    @Autowired
    ConfigBean configBean;


    @Value("${com.lyyco.name}")
    private String name;
    @Value("${com.lyyco.want}")
    private String want;
    @RequestMapping("/hello")
    public String hello(){
        return name+want+env.getProperty("com.lyyco.name");
    }
}
