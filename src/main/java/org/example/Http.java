package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
    private final String username;
    private final ParseJSON jsonParser;
    Http(String username, ParseJSON jsonParser){
        this.jsonParser=jsonParser;
        this.username = username;
    }
    public void run(){
        String url = "https://api.github.com/users/"+username+"/events";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        try {
             request = HttpRequest.newBuilder().GET()
                    .uri(new URI(url))
                    .header("User-agent","my cli app")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()){
                case 200:
                    jsonParser.parseJSON(response);
                    break;
                case 301:
                    System.out.println("301 what?, i don't know-> you figure it out");
                    break;
                case 304:
                    System.out.println("304 what?, i don't know-> you figure it out");
                    break;
                case 403:
                    System.out.println("Forbidden your stupid ass");
                    break;
                case 404:
                    System.out.println("you stupiddd(asian accent)");
                    break;
                default:
                    System.out.println("let's just say i am really a bad programmer ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
