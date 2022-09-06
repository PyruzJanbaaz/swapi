package com.pyruz.dzm.swapi.handler.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("The Star Wars API")
                .description("The Star Wars API, or \"swapi\" (Swah-pee) is the world's first quantified and programmatically-accessible data source for all the data from the Star Wars canon universe")
                .version("1.0.0")
                .license("Pyruz Janbaaz License")
                .licenseUrl("https://www.linkedin.com/in/pyruz-janbaaz-9a327226/")
                .contact(
                        new Contact(
                                "Pyruz Janbaaz",
                                "https://www.linkedin.com/in/pyruz-janbaaz-9a327226/",
                                "Pyruz.Janbaaz@gmail.com"))
                .build();
    }
}
