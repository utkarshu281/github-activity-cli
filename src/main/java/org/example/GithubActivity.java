package org.example;
public class GithubActivity {
    public static void main(String[] args) {
        if(args.length==0){
            throw new IllegalArgumentException("need to provide an argument");
        }
        String Username = args[0];
        ParseJSON jsonParser = new ParseJSON();
        Http fetchApi = new Http(Username,  jsonParser);
        fetchApi.run();
    }
}
