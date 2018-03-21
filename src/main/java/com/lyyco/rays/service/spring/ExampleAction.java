package com.lyyco.rays.service.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试单例
 * com.banma.ota.ctrip.controller
 *
 * @Author liyangyang
 * 2018/3/21
 */
@Controller
@RequestMapping(value = "/lyy")
//@Scope("prototype")
public class ExampleAction {

    private int singletonInt = 1;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String singleton(HttpServletRequest request,
                            HttpServletResponse response)
            throws Exception {
        String data=request.getParameter("data");
        if(data!=null&&data.length()>0){
            try{
                int paramInt= Integer.parseInt(data);
                singletonInt = singletonInt + paramInt;
            }
            catch(Exception ex){
                singletonInt+=10;
            }
        }else{
            singletonInt+=1000;
        }
        return String.valueOf(singletonInt);
    }
    @RequestMapping(value = "/sleepdata")
    @ResponseBody
    public String switcher(HttpServletRequest request
            , HttpServletResponse response)
            throws Exception {
        String sleep = request.getParameter("sleep");
        if (sleep.equals("on")) {
            Thread.currentThread().sleep(100000);
            return "sleep on";
        } else {
            return sleep;
        }
    }
}
