package com.vueadmin.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket Lamp() {

        Parameter token = new ParameterBuilder().name("token")  //全局参数
                .description("用户登陆令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(true)
                .build();
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(token);
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo())
                .groupName("灯")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.iaiyou.ssj.controller"))
                .paths(Predicates.or(
                        PathSelectors.regex("/lamp.*"),
                        PathSelectors.regex("/lampState.*")
                ))
                .build();
    }
    @Bean
    public Docket LampState() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("获取Token")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.iaiyou.ssj.controller"))
                .paths(PathSelectors.ant("/GetToken"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("shiro-jwt-redis")
                .description("api")
                // 作者信息
                .contact(new Contact("漫长", "http://www.iaiyou.top", "1797109307@qq.com"))
                .version("1.0.0")
                .build();
    }
}
