package com.example;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.example.mapper")
@RestController
//@NacosPropertySource(dataId = "lcytest", autoRefreshed = true)
@EnableDiscoveryClient
@Slf4j
@ServletComponentScan(basePackages = "com.example.filter")
public class DemoApplication {

//	@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	private boolean useLocalCache;

//	@NacosValue(value = "${message:ddd}", autoRefreshed = true)
	private String message;

	/**
	 * 启动文件
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/hello")
	public boolean hello(){
		return useLocalCache;
	}

	@RequestMapping("/getMessage")
	public String getMessage(){
		return message;
	}
}
