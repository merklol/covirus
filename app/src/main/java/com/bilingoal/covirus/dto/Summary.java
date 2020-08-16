package com.bilingoal.covirus.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Summary {
    @SerializedName("Global")
    private final Global global;
    @SerializedName("Countries")
    private final List<Country> countries;

    public Summary(Global global, List<Country> countries) {
        this.global = global;
        this.countries = countries;
    }

    public Global getGlobal() {
        return global;
    }

    public List<Country> getCountries() {
        return countries;
    }
}
