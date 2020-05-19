package com.ddz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ddz.domain.mapper")
public class VoiceWxMiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceWxMiniApplication.class, args);
		System.out.println("VoiceWxMini Running Success!");
	}

}
