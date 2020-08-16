package com.bilingoal.virustracker.dto;

public class Article {
    private final String title;
    private final String body;
    private final String link;

    public Article(String title, String body, String link) {
        this.title = title;
        this.body = body;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getLink() {
        return link;
    }
}
