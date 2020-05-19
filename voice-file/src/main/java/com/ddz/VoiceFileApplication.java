package com.ddz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ddz.domain.mapper")
public class VoiceFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceFileApplication.class, args);
		System.out.println("VoiceFile Running Success!");
	}

}
