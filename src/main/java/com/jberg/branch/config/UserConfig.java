package com.jberg.branch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class UserConfig {

    private static final String GITHUB_USERS_BASE_URL = "https://api.github.com/users/";

    @Bean
    public RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl(GITHUB_USERS_BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
