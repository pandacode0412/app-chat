package com.project.chatapp.request;

public class SingleChatReques {
    private Integer userId;

    public SingleChatReques() {

    }

    public SingleChatReques(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
