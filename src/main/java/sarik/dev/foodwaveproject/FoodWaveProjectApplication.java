package sarik.dev.foodwaveproject;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import sarik.dev.foodwaveproject.configuration.SessionUser;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
@EnableJpaAuditing
public class FoodWaveProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodWaveProjectApplication.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorProvider(SessionUser sessionUser) {
        return () -> Optional.of(sessionUser.getCurrentUser().getId());
    }
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Food Wave Project")
                        .description("Pullik Project")
                        .version("10")
                        .contact(new Contact()
                                .name("42")
                                .email("john.lgd65@gmail.com")
                                .url("https://github.com/sardor-kushakov/food-wave-project"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:9090").description("Production Server"),
                        new Server()
                                .url("http://localhost:8080").description("Test Server")
                )).addSecurityItem(new SecurityRequirement().addList("basicAuth", "bearerAuth"))
                .components(new Components()
//                        .addSecuritySchemes("basicAuth", new SecurityScheme()
//                                .name("basicAuth")
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("basic"))
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                );

    }

}
