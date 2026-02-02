package com.example.baitap.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Lấy đường dẫn tuyệt đối đến thư mục images
        String uploadPath = "file:" + Paths.get(System.getProperty("user.dir"),
                "target", "classes", "static", "images").toAbsolutePath() + "/";

        System.out.println("🌐 Cấu hình static resource handler:");
        System.out.println("   URL Pattern: /images/**");
        System.out.println("   File Location: " + uploadPath);

        // Cấu hình Spring Boot serve file từ /images/**
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);

        // Giữ lại cấu hình mặc định cho các static resources khác
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/public/");
    }
}
