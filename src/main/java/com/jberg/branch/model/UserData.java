package com.jberg.branch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"user_name", "display_name", "avatar", "geo_location", "email", "url", "created_at", "repos"})
public class UserData {
    @JsonAlias("login")
    public String user_name;

    @JsonAlias("name")
    public String display_name;

    @JsonAlias("avatar_url")
    public String avatar;

    @JsonAlias("location")
    public String geo_location;

    public String email;
    public String url;
    public String created_at;
    public List<UserRepos> repos;
}
