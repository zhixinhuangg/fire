package com.dmsoft.fire;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("fire")
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .pathMapping("/")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.dmsoft.fire.openapi.v1"))
            .paths(PathSelectors.any())
            .build()
            ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("培训 APIs")
            .termsOfServiceUrl("http://www.baidu.com/")
            .contact(new Contact("master", "http://www.huimeifeng2012.com/", "huimeifeng@disannvshen.com"))
            .version("1.0")
            .build();
    }


}
