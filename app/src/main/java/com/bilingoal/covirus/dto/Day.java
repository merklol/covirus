package com.bilingoal.covirus.dto;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("Country")
    private final String country;
    @SerializedName("CountryCode")
    private final String countryCode;
    @SerializedName("Province")
    private final String province;
    @SerializedName("City")
    private final String city;
    @SerializedName("CityCode")
    private final String cityCode;
    @SerializedName("Lat")
    private final String lat;
    @SerializedName("Lon")
    private final String lon;
    @SerializedName("Cases")
    private final int cases;
    @SerializedName("Status")
    private final String status;
    @SerializedName("Date")
    private final String date;

    public Day(String country, String countryCode, String province, String city, String cityCode, String lat, String lon, int cases, String status, String date) {
        this.country = country;
        this.countryCode = countryCode;
        this.province = province;
        this.city = city;
        this.cityCode = cityCode;
        this.lat = lat;
        this.lon = lon;
        this.cases = cases;
        this.status = status;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public int getCases() {
        return cases;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
