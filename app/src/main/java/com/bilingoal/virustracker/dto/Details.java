package com.bilingoal.virustracker.dto;

public class Details {
    private final String title;
    private final int numberOfCases;
    public static final String TOTAL_CONFIRMED = "Total Confirmed";
    public static final String NEW_CONFIRMED = "New Confirmed";
    public static final String TOTAL_RECOVERED = "Total Recovered";
    public static final String NEW_RECOVERED = "New Recovered";
    public static final String TOTAL_DEATHS = "Total Deaths";
    public static final String NEW_DEATHS = "New Deaths";

    public Details(String title, int numberOfCases) {
        this.title = title;
        this.numberOfCases = numberOfCases;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfCases() {
        return numberOfCases;
    }
}
