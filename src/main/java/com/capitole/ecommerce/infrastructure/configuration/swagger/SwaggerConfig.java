package com.capitole.ecommerce.infrastructure.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

   @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.capitole.ecommerce.infrastructure.rest.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfoMetaData())
                .tags(new Tag("Prices", "API associated with obtaining product prices"))
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("Ecommerce Prices API Documentation")
                .description("get the Final price (pvp) and the rate that applies to a product from a chain between certain dates")
                .contact(new Contact("Ing Blanco", "https://www.linkedin.com/in/federico-blanco-ing/", "federicoblanco1986@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }

}
