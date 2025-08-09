package com.example.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * This method is overridden to return the name of the currently logged in user.
     * It is currently hardcoded to return "Admin_microService".
     *
     * @return The name of the currently logged in user.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Admin_microService");
    }
}
