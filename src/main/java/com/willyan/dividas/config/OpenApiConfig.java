package com.willyan.dividas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI dividasOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Controle de Dividas API")
						.description("Documentacao da API para controle de dividas.")
						.version("v1")
						.contact(new Contact()
								.name("Willyan Anjos"))
						.license(new License()
								.name("MIT")));
	}
}
