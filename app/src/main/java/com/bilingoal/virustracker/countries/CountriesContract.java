package com.bilingoal.virustracker.countries;

import com.bilingoal.virustracker.base.BaseContract;
import com.bilingoal.virustracker.dto.Country;

import java.util.List;

public interface CountriesContract {
    interface Presenter {

    }

    interface View extends BaseContract.View {
        void displayListOfCountries(List<Country> countries);
    }
}
