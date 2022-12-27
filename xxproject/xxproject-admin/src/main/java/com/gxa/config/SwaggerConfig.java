package com.gxa.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {



    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            //加了ApiOperation注解的类，才生成接口文档
//            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            //包下的类，才生成接口文档
            .apis(getBasePackages())
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(security());

    }
    private Predicate<RequestHandler> getBasePackages() {
        Predicate<RequestHandler> predicate = Predicates.and(RequestHandlerSelectors.basePackage("com.gxa.modules.pay.controller"));  // 扫描com.fan.ams.controller包

        predicate = Predicates.or(RequestHandlerSelectors.basePackage("com.gxa.modules.sys.controller"), predicate);// 扫描com.fan.ams.controller222包，和上面是并集
        predicate = Predicates.or(RequestHandlerSelectors.basePackage("com.gxa.oss.controller"), predicate);
        return predicate;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("xx-project")
            .description("xx-project文档")
            .termsOfServiceUrl("https://www.baidu.com")
            .version("3.0.0")
            .build();
    }


    private List<ApiKey> security() {
        List<ApiKey> keys = new ArrayList<>();
        ApiKey apiKey = new ApiKey("token", "token", "header");
        keys.add(apiKey);
        return keys;
    }

}