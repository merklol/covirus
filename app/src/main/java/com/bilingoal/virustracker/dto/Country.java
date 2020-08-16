package com.bilingoal.virustracker.dto;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("Country")
    private final String name;
    @SerializedName("CountryCode")
    private final String code;
    @SerializedName("Slug")
    private final String slug;
    @SerializedName("NewConfirmed")
    private final int newConfirmed;
    @SerializedName("TotalConfirmed")
    private final int totalConfirmed;
    @SerializedName("NewDeaths")
    private final int newDeaths;
    @SerializedName("TotalDeaths")
    private final int totalDeaths;
    @SerializedName("NewRecovered")
    private final int newRecovered;
    @SerializedName("TotalRecovered")
    private final int totalRecovered;
    @SerializedName("Date")
    private final String date;

    public Country(String name, String code, String slug, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered, String date) {
        this.name = name;
        this.code = code;
        this.slug = slug;
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSlug() {
        return slug;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public String getDate() {
        return date;
    }
}
