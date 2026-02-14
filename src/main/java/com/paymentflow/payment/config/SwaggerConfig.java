package com.paymentflow.payment.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Collections;


@Configuration
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.paymentflow.payment.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "Payment System API",
//                "APIs for PaymentFlow Application",
//                "1.0",
//                "Terms of service URL",
//                new Contact("Pralhad", "algo2ace.com", "email@example.com"),
//                "License",
//                "License URL",
//                Collections.emptyList()
//        );
//    }
@Bean
public GroupedOpenApi userApi() {
    return GroupedOpenApi.builder()
            .group("User APIs")
            .packagesToScan("com.paymentflow.payment.controller")
            .build();
}
}
