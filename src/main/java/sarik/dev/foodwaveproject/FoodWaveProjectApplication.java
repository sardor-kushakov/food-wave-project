package sarik.dev.foodwaveproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import sarik.dev.foodwaveproject.configuration.SessionUser;

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

}
