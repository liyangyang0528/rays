package com.lyyco.rays;

import com.lyyco.rays.web.config.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties({ConfigBean.class})
public class RaysApplication {

	public static void main(String[] args) {


		SpringApplication.run(RaysApplication.class, args);
	}
}
