package com.yedam.app.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Value("${file.upload.path}")
	private String uploadPath;

	// WebMvcConfigurer Override 가 안뜬다 내부 추상 method 가 없다
	// source Override 처리 한다
	// 리소스 핸들링
	// src/main/resources 밑 static 폴더 와 이름 다르게 지정 해줘야 된다
	// 외부 경로 설정 을 하면 가져와 줄 수 있다
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub

		registry.addResourceHandler("/images/**") // URL
				.addResourceLocations("file:///" + uploadPath, ""); // 파일 접근, 실제 경로 (,콤마 이것을 이용해 여러 경로 처리 가능)

	}

}
