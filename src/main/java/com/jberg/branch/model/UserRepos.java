package com.jberg.branch.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"name", "url"})
public class UserRepos {
    public String name;
    public String url;
}
