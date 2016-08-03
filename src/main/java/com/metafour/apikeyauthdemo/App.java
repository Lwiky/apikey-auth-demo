package com.metafour.apikeyauthdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metafour.apikeyauthdemo.entity.ApiKey;
import com.metafour.apikeyauthdemo.security.ApiKeyUtils;

@SpringBootApplication
@RestController
public class App {

	@RequestMapping("/")
	@ResponseBody ApiKey index() {
		return ApiKeyUtils.getPrincipal();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
