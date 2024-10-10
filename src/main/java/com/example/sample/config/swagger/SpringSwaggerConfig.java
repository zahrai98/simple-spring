//package com.example.sample.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.VendorExtension;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//import java.util.ArrayList;
////
////@Configuration
////public class SwaggerConfig {
////
////    @Bean
////    public Docket atividadeApi() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.basePackage("com.example.example"))
////                .paths(regex("/api.*"))
////                .build()
////                .apiInfo(metaInfo());
////    }
////
////    private ApiInfo metaInfo() {
////
////        ApiInfo apiInfo = new ApiInfo(
////                "Atividades API REST",
////                "API REST de cadastro de atividades.",
////                "1.0",
////                "Terms of Service",
////                new Contact("João VR", "www.una.br/",
////                        " "),
////                "Apache License Version 2.0",
////                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
////        );
////
////        return apiInfo;
////    }
////}
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//
//@Configuration
//public class SpringSwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.sample"))
//                .paths(PathSelectors.regex("/.*"))
//                .build().
//                apiInfo(apiInfoMetaData()
//                );
//    }
//
//    private ApiInfo apiInfoMetaData() {
//
//        return new ApiInfoBuilder().title("NAME OF SERVICE")
//                .description("API Endpoint Decoration")
//                .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//                .version("1.0.0")
//                .build();
//    }
//}
