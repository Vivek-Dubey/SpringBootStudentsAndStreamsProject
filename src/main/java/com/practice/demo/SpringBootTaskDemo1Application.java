package com.practice.demo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootTaskDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTaskDemo1Application.class, args);
		System.out.println("Application up!!!");
	}
	
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.practice.demo"))
				.build()
				.apiInfo(apiDetails());
				
	}
	
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Student Information API",
				"Sample SpringBoot API Task_1",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Vivek Dubey","","vivek.dubey@xoriant.com"),
				"App License",
				"https://abc.com",
				Collections.emptyList());
				
	}

}
