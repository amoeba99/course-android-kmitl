package com.demo.lazy.instagram.model;

/**
 * Created by Amoeba on 10/15/2017.
 */

public class Posts {
    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private int comment;
    private int like;
    private String url;
}
