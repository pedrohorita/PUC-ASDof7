package com.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.control"))
                .paths(regex("/livro.*"))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Arquitetura de Software Distribuído – Oferta 7",
                "Arquitetura de Backend e Microsserviços - Atividade Aula 03 - Parte 2, Aplicação Baseada no Estilo Arquitetural de API",
                "1.0",
                "Terms of service",
                new Contact("Pedro R T A Horita", "	", "pedro_horita@hotmail.com"),
               "",
                "");
        return apiInfo;
    }

}