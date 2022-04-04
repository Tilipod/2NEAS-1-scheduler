package ru.tilipod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZonedDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class NneasSchedulerApplication {

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }

    public static void main(String[] args) {
            SpringApplication.run(NneasSchedulerApplication.class, args);
        }
}
