package com.jberg.branch.utility;

import com.jberg.branch.model.UserData;
import com.jberg.branch.model.UserRepos;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    public final String TEST_USERNAME = "test-user";

    public UserData getMockUserDataResponse() {
        UserData userData = getMockUserData();
        userData.setRepos(getMockUserRepos());

        return  userData;
    }

    public List<UserRepos> getMockUserRepos() {
        List<UserRepos> userReposList = new ArrayList<>();

        UserRepos repo1 = new UserRepos();
        repo1.setName("repo-1");
        repo1.setUrl("repo-1.test");

        UserRepos repo2 = new UserRepos();
        repo2.setName("repo-2");
        repo2.setUrl("repo-2.test");

        userReposList.add(repo1);
        userReposList.add(repo2);

        return userReposList;
    }

    public UserData getMockUserData() {
        UserData userData = new UserData();
        userData.setUser_name(TEST_USERNAME);
        userData.setDisplay_name("Test User");
        userData.setAvatar("avatar.test");
        userData.setGeo_location("San Diego");
        userData.setEmail("test-user@email.test");
        userData.setUrl("test-user.test");
        userData.setCreated_at("2026-07-21");

        return userData;
    }
}
