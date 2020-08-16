package com.bilingoal.covirus.details;

import com.bilingoal.covirus.base.BaseContract;
import com.bilingoal.covirus.dto.Country;
import com.bilingoal.covirus.dto.Details;
import com.github.mikephil.charting.data.Entry;

import java.util.List;

public interface DetailsContract {

    interface Presenter extends BaseContract.Presenter<DetailsContract.View> {
        void displayData(Country country);
        void loadCases();
        void clearDisposables();
    }

    interface View extends BaseContract.View {
        void displayCountryName(String countryName);
        void displayDate(String date);
        void displayDetails(List<Details> details);
        void onParseDateError();
        void displayGraph(List<Entry> entries);
        void onInternetConnectionError();
    }
}
