package br.com.startideia.vuttr.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("VUTTR API")
                        .description("VUTTR API developed for Startideia Selection Proccess")
                .contact(getContact()));
    }

    private Contact getContact() {
        Contact contact = new Contact();

        contact.setName("Sandney Farias da Cunha");
        contact.setEmail("sandneyfarias@hotmail.com");

        return contact;
    }

}
