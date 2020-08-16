package com.bilingoal.virustracker.countries;

import com.bilingoal.virustracker.base.BasePresenter;
import com.bilingoal.virustracker.dto.Country;
import com.bilingoal.virustracker.models.StateManager;

import javax.inject.Inject;
import java.util.List;

public class CountriesPresenter extends BasePresenter<CountriesContract.View> implements CountriesContract.Presenter {
    private final StateManager stateManager;

    @Inject
    public CountriesPresenter(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void createListOfCountries() {
        if(stateManager.getSummary() != null) {
            List<Country> countries = stateManager.getSummary().getCountries();
            view.displayListOfCountries(countries);
        }
    }

    public void selectCountry(Country country) {
        stateManager.setSelectedCountry(country);
    }
}
