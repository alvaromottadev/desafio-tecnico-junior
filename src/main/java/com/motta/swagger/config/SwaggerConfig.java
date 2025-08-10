package com.motta.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Junior Technical Challenge")
                .version("1.0.0")
                .description("Junior Technical Challenge API"))
                .tags(
                        Arrays.asList(
                                new Tag().name("FuelController").description("Endpoints for managing fuels"),
                                new Tag().name("FuelPumpController").description("Endpoints for managing fuel pumps"),
                                new Tag().name("FuelSupplyController").description("Endpoints for managing fuel supplies")
                        )
                );
    }

}
