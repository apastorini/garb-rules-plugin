package com.walgreens.openapivalidator;

import java.util.List;

import org.springframework.web.reactive.function.server.ServerRequest;

class MyError {
    private ServerRequest request;
    private String id;
    private List<String> messages;

    public MyError(ServerRequest request, String id, List<String> messages) {
        this.request = request;
        this.id = id;
        this.messages = messages;
    }
    public ServerRequest getRequest() {
        return request;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
