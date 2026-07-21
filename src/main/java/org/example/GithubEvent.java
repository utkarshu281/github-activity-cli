package org.example;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubEvent(
        String id,
        String type,
        Actor actor,
        Repo repo,
        JsonNode payload,
        @JsonProperty("public") boolean isPublic,
        @JsonProperty("created_at") String createdAt
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Actor(
            String login
    ){ }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Repo(
            String name
    ){}

}
