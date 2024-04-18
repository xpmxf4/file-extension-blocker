package com.flow.fileextensionblocker;

import com.flow.fileextensionblocker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class FileExtensionBlockerApplication implements CommandLineRunner {

    private final FileExtensionService extensionService;

    public static void main(String[] args) {
        SpringApplication.run(FileExtensionBlockerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        extensionService.setDefaultData();
    }
}
