package com.example.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/* these annotations needed when AccountsApplication and all other packages are packed separately
@ComponentScans({ @ComponentScan("com.example.accounts.controller") })
@EnableJpaRepositories("com.example.accounts.repository")
@EntityScan("com.example.accounts.model")*/
/**
 * @EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
 * It says to spring boot framework to please active JPA auditing and
 * please leverage the bean with the bean auditAwareImpl
 * to understand current auditor
 */
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "H-Bank Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Hemchandh",
                        email = "123@gmail.com",
                        url = "https://www.hbank.com"
                )
        )
)

public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
