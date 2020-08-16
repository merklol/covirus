package com.bilingoal.covirus.models;

import com.bilingoal.covirus.dto.Article;
import com.bilingoal.covirus.dto.Country;
import com.bilingoal.covirus.dto.Summary;
import com.bilingoal.covirus.dto.Version;
import com.github.mikephil.charting.data.Entry;
import java.util.List;

public class StateManager {
    private Country selected;
    private Summary summary;
    private boolean adLoaded = false;
    private final Version version = new Version();
    private List<Article> latestArticles;
    private List<Entry> entries;
    private static StateManager repository;

    private StateManager() { }

    public static synchronized StateManager getInstance() {
        if(repository == null) repository = new StateManager();
        return repository;
    }

    public void setSelectedCountry(Country country){
        this.selected = country;
    }

    public Country getSelected() {
        return selected;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Article> getLatestArticles() {
        return latestArticles;
    }

    public void setLatestArticles(List<Article> latestArticles) {
        this.latestArticles = latestArticles;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {

        this.entries = entries;
    }

    public boolean isAdLoaded() {
        return adLoaded;
    }

    public void setAdLoaded(boolean adIsLoaded) {
        this.adLoaded = adIsLoaded;
    }

    public Version getVersion() {
        return version;
    }
}
