package com.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.springjpa.repo.CustomerRepository;

@SpringBootApplication
public class SpringJpaPostgreSqlApplication  extends SpringBootServletInitializer  implements CommandLineRunner{

	@Autowired
	CustomerRepository repository;
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringJpaPostgreSqlApplication.class);
    }
	public static void main(String[] args){
		SpringApplication.run(SpringJpaPostgreSqlApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// clear all record if existed before do the tutorial with new data 
		repository.deleteAll();
	}
}
