package com.example.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/**
 * @EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
 * It says to spring boot framework to please active JPA auditing and
 * please leverage the bean with the bean auditAwareImpl
 * to understand current auditor
 */
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")

public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
