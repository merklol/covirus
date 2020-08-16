package com.bilingoal.virustracker.details;

import android.util.Log;
import com.bilingoal.virustracker.base.BasePresenter;
import com.bilingoal.virustracker.dto.Country;
import com.bilingoal.virustracker.dto.Day;
import com.bilingoal.virustracker.dto.Details;
import com.bilingoal.virustracker.models.DetailsList;
import com.bilingoal.virustracker.models.StateManager;
import com.bilingoal.virustracker.network.NetworkInterface;
import com.bilingoal.virustracker.utils.DateParser;
import com.bilingoal.virustracker.utils.schedulerproviders.SchedulerProvider;
import com.github.mikephil.charting.data.Entry;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java9.util.Comparators;
import java9.util.stream.Collectors;
import java9.util.stream.StreamSupport;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DetailsPresenter extends BasePresenter<DetailsContract.View> implements DetailsContract.Presenter {
    private final NetworkInterface networkInterface;
    private final StateManager stateManager;
    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable bag = new CompositeDisposable();

    @Inject
    public DetailsPresenter(NetworkInterface networkInterface, StateManager stateManager,
                            SchedulerProvider schedulerProvider) {
        this.networkInterface = networkInterface;
        this.stateManager = stateManager;
        this.schedulerProvider = schedulerProvider;
    }

    private void displayDate(Country country){
        String date = DateParser.parseDate(country);
        if(!date.equals(DateParser.UNKNOWN_DATE)){
            view.displayDate(date);
        } else {
            view.onParseDateError();
        }
    }

    private void createDetailList(Country country) {
        DetailsList detailsList = new DetailsList.Builder()
                .add(Details.TOTAL_CONFIRMED, country.getTotalConfirmed())
                .add(Details.NEW_CONFIRMED, country.getNewConfirmed())
                .add(Details.TOTAL_RECOVERED, country.getTotalRecovered())
                .add(Details.NEW_RECOVERED, country.getNewRecovered())
                .add(Details.TOTAL_DEATHS, country.getTotalDeaths())
                .add(Details.NEW_DEATHS, country.getNewDeaths())
                .build();
        view.displayDetails(detailsList.get());
    }

    @Override
    public void displayData(Country country) {
        view.displayCountryName(country.getName());
        displayDate(country);
        createDetailList(country);

    }

    @Override
    public void loadCases() {
        if(stateManager.getEntries() == null) {
            Observable<List<Day>> cases = networkInterface
                    .getCasesSinceDayOne(stateManager.getSelected().getSlug());

            Disposable d = cases.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(this::onLoaded, error -> view.onInternetConnectionError());

            bag.add(d);
        } else {
            view.displayGraph(stateManager.getEntries());
        }
    }

    @Override
    public void clearDisposables() {
        bag.clear();
    }

    private List<Entry> createEntriesForLastTwoWeeks(List<Day> days) {
        List<Day> tempList = StreamSupport.stream(days)
                .sorted(Comparators.comparing(Day::getCases))
                .collect(Collectors.toList());
        tempList =  tempList.subList(days.size() - 14, days.size());

        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i < tempList.size(); i ++) {
            entries.add(new Entry(i + 1, tempList.get(i).getCases()));
        }

        return entries;
    }

    private void onLoaded(List<Day> days) {
        List<Entry> entries = createEntriesForLastTwoWeeks(days);
        stateManager.setEntries(entries);
        view.displayGraph(entries);
    }
}
