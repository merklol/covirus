package com.bilingoal.covirus.models;

import com.bilingoal.covirus.dto.Details;

import java.util.ArrayList;
import java.util.List;

public class DetailsList {
    private final List<Details> detailsList;

    private DetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    public List<Details> get() {
        return detailsList;
    }

    public static class Builder {
        private final List<Details> detailsList = new ArrayList<>();

        public Builder add(String title, int numberOfCases) {
            detailsList.add(new Details(title, numberOfCases));
            return this;
        }

        public DetailsList build() {
            return new DetailsList(detailsList);
        }
    }
}
