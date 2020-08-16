package com.bilingoal.covirus.dto;

import java.util.List;

public class Response {
    private Summary summary;
    private List<Article> articleList;

    public Response() { }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public static Response createDefaultResponse() {
        return new Response();
    }
}
