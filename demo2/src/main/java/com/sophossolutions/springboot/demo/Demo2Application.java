package com.sophossolutions.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.sophossolutions.springboot.demo"})
//@EntityScan("com.sophossolutions.springboot.demo.model")
//@EnableJpaRepositories("com.sophossolutions.springboot.demo.repository")
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

}
