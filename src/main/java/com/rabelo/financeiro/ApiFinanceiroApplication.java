package com.rabelo.financeiro;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rabelo.financeiro.config.property.FinanceiroApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(FinanceiroApiProperty.class)
public class ApiFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFinanceiroApplication.class, args);
		
	}
	
	
	  @PostConstruct 
	  void started() {
	  TimeZone.setDefault(TimeZone.getTimeZone("UTC")); 
	  }
	 

}
