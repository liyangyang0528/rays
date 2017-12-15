package com.lyyco.rays.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by lyy on 2017/12/15.
 */
@ConfigurationProperties(prefix="com.lyyco")
//下面的两个注解可以指定哪个配置文件
//@Configuration
//@PropertySource("classpath:application-dev.properties")
@Setter
@Getter
public class ConfigBean {

    private String name;

    private String want;
}
