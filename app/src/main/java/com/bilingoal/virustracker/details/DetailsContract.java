package com.bilingoal.virustracker.details;

import com.bilingoal.virustracker.base.BaseContract;
import com.bilingoal.virustracker.dto.Country;
import com.bilingoal.virustracker.dto.Details;
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
