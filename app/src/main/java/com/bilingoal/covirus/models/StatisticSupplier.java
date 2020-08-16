package com.bilingoal.covirus.models;

import com.bilingoal.covirus.dto.Country;
import com.bilingoal.covirus.dto.Summary;
import java9.util.Comparators;
import java9.util.stream.Collectors;
import java9.util.stream.StreamSupport;

import java.util.Collections;
import java.util.List;

public class StatisticSupplier {
    private final Summary summary;
    private static final int NUMBER_OF_COUNTRIES = 5;

    public StatisticSupplier(Summary summary) {
        this.summary = summary;
    }

    public List<Country> getTop5List(){
        List<Country> countryList = StreamSupport.stream(summary.getCountries())
                .sorted(Comparators.comparing(Country::getTotalConfirmed))
                .collect(Collectors.toList());
        countryList = countryList.subList(Math.max(countryList.size() - NUMBER_OF_COUNTRIES, 0), countryList.size());
        Collections.reverse(countryList);
        return countryList;
    }
}
