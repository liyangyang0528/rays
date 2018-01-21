package com.lyyco.rays;

import com.lyyco.rays.web.config.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties({ConfigBean.class})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class RaysApplication {

	public static void main(String[] args) {


		SpringApplication.run(RaysApplication.class, args);
	}
}
