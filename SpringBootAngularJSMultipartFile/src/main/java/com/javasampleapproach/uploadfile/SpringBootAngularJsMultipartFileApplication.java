package com.javasampleapproach.uploadfile;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.javasampleapproach.uploadfile.storage.StorageService;


@SpringBootApplication
public class SpringBootAngularJsMultipartFileApplication extends SpringBootServletInitializer
		implements CommandLineRunner {

	@Resource
	StorageService storageService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootAngularJsMultipartFileApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootAngularJsMultipartFileApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

}