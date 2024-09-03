package com.example.companydirectory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CompanyDirectoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyDirectoryApplication.class, args);
    }

}
