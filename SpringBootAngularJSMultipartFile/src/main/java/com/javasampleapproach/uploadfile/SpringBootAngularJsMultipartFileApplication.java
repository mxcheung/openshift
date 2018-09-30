package com.javasampleapproach.uploadfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SpringBootAngularJsMultipartFileApplication extends SpringBootServletInitializer
		{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootAngularJsMultipartFileApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootAngularJsMultipartFileApplication.class, args);
	}

	

}