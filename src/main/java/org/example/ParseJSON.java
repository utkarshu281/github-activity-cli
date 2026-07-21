package org.example;
import java.util.List;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

public class ParseJSON {
    public void parseJSON(HttpResponse<String> response){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<GithubEvent> eventList = objectMapper.readValue(response.body(), new TypeReference<List<GithubEvent>>(){});
            for(GithubEvent event : eventList){
                    String eventType = event.type();
                    String createdAt = event.createdAt();
                    String repoName = event.repo().name();
                    String actorName = event.actor().login();
                    JsonNode payload = event.payload();
//                    if(payload != null && payload.has("action")){
//                        String action = payload.get("action").asText();
//                        System.out.println("  -> Action: " + action);
//                    }
                switch (eventType){
                    case "PushEvent":
                        int countNumberOfPushes=0;
                        if(payload!=null && payload.has("size")){
                            countNumberOfPushes = payload.get("size").intValue();
                        }
                        System.out.printf("-pushed %d commits to %s%n",countNumberOfPushes,repoName);
                        break;
                    case "WatchEvent":
                        System.out.printf("-starred %s%n",repoName);
                        break;
                    case "CreateEvent":
                        String eventCreated="";
                        JsonNode repoPayload = event.payload();
                        if(repoPayload!=null && repoPayload.has("ref_type")){
                            eventCreated = repoPayload.get("ref_type").asText();
                        }
                        System.out.printf("- Created a new %s in %s and it created at %s%n", eventCreated, repoName,createdAt);
                        break;

                    default:
                        // A catch-all for any other weird event types
                        System.out.printf("- %s Performed %s on %s%n",actorName, eventType, repoName);
                        break;

                }
                System.out.println("------------------------------------------");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // i need to understand this again
    }
}
