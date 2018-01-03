package com.lyyco.rays.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by lyy on 2018/1/2.
 */
@Service
public class DemoServiceImpl implements DemoService {
   @Override
   public String sayHello(String name){
        return "Hello" + name;
   }
}
