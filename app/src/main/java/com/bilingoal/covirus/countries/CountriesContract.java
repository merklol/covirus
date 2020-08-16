package com.bilingoal.covirus.countries;

import com.bilingoal.covirus.base.BaseContract;
import com.bilingoal.covirus.dto.Country;

import java.util.List;

public interface CountriesContract {
    interface Presenter {

    }

    interface View extends BaseContract.View {
        void displayListOfCountries(List<Country> countries);
    }
}
