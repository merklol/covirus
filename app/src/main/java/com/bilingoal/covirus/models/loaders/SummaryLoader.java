package com.bilingoal.covirus.models.loaders;

import com.bilingoal.covirus.dto.Response;
import com.bilingoal.covirus.dto.Summary;
import com.bilingoal.covirus.network.NetworkInterface;
import com.bilingoal.covirus.utils.schedulerproviders.SchedulerProvider;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class SummaryLoader extends Loader {
    private final NetworkInterface networkInterface;
    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable bag = new CompositeDisposable();

    public SummaryLoader(NetworkInterface networkInterface, SchedulerProvider schedulerProvider) {
        this.networkInterface = networkInterface;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void load(Response response) {
            Disposable d = networkInterface.getSummary().subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(summary -> {
                        onLoaded(summary, response);
                    }, this::handleError);
            bag.add(d);
    }

    public void clearDisposables() {
        bag.clear();
    }

    private void onLoaded(Summary summary, Response response){
        if(summary != null) {
            response.setSummary(summary);
            this.next(response);
        } else {
            failureListener.onFailure();
        }
    }

    private void handleError(Throwable t){
        if(t != null) failureListener.onFailure();
    }
}
