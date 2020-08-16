package com.bilingoal.virustracker.dto;

import com.google.gson.annotations.SerializedName;

public class Global {
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

    public Global(int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered) {
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
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
}
