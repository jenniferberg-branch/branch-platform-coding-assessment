package com.jberg.branch.service;

import com.jberg.branch.model.UserData;
import com.jberg.branch.model.UserRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final RestClient restClient;

    public UserService(RestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * Get the user data by username from the GitHub endpoints.
     *
     * @param username - username
     * @return UserData
     */
    public UserData getUserData(String username) throws Exception {
        try {
            UserData userData = this.getGithubUserData(username);
            List<UserRepos> userRepos = this.getGithubReposData(username);

            userData.setRepos(userRepos);

            return userData;
        } catch (Exception e) {
            log.error("Unable to retrieve user data for user {}", username, e);
            throw e;
        }
    }

    private UserData getGithubUserData(String username) throws Exception {
        return restClient.get()
                .uri("/{username}", username)
                .retrieve()
                .body(UserData.class);
    }

    private List<UserRepos> getGithubReposData(String username) throws Exception {
        return restClient.get()
                .uri("/{username}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
