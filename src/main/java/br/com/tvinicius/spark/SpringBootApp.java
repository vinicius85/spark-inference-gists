package br.com.tvinicius.spark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class SpringBootApp {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

}
