package com.javasampleapproach.uploadfile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
    @GetMapping("/blah")
    public String index() {
   		log.info("index start");
        return "upload";
    }

}