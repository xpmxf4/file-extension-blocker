package com.flow.fileextensionblocker.config;

import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;

import java.util.*;

@Configuration
public class AuditingConfiguration {

    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.of("SYSTEM");
    }
}
