package com.xiao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * Knife4j 配置类
 */
@Configuration
public class Knife4jConfig {

    /**
     * 创建API分组 - 核心接口
     */
    @Bean
    public GroupedOpenApi coreApi() {
        return GroupedOpenApi.builder()
                .group("1-核心接口")
                .pathsToMatch("/api/**")
                .packagesToScan("com.xiao.controller")
                .build();
    }

    /**
     * 创建API分组 - 知识库接口
     */
    @Bean
    public GroupedOpenApi knowledgeBaseApi() {
        return GroupedOpenApi.builder()
                .group("2-知识库接口")
                .pathsToMatch("/api/kb/**")
                .packagesToScan("com.xiao.controller")
                .build();
    }

    /**
     * 创建API分组 - 会话接口
     */
    @Bean
    public GroupedOpenApi conversationApi() {
        return GroupedOpenApi.builder()
                .group("3-会话接口")
                .pathsToMatch("/api/conversation/**")
                .packagesToScan("com.xiao.controller")
                .build();
    }

    /**
     * 创建API接口信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        // 配置认证方式
        Components components = new Components();
        components.addSecuritySchemes("Bearer Token", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION));

        // 创建API基本信息
        return new OpenAPI()
                .components(components)
                .addSecurityItem(new SecurityRequirement().addList("Bearer Token"))
                .info(new Info()
                        .title("RAGFlow API文档")
                        .description("RAGFlow系统API接口文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("RAGFlow团队")
                                .email("support@ragflow.com")
                                .url("https://www.ragflow.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
} 