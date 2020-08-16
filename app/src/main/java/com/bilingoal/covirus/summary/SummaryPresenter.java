package com.bilingoal.covirus.summary;

import android.content.Context;
import com.bilingoal.covirus.dto.Article;
import com.bilingoal.covirus.models.InterstitialAdBuilder;
import com.bilingoal.covirus.models.StatisticSupplier;
import com.bilingoal.covirus.base.BasePresenter;
import com.bilingoal.covirus.dto.Country;
import com.bilingoal.covirus.dto.Summary;
import com.bilingoal.covirus.models.StateManager;
import com.bilingoal.covirus.utils.Constants;
import com.google.android.gms.ads.AdRequest;

import javax.inject.Inject;
import java.util.List;


public class SummaryPresenter extends BasePresenter<SummaryContract.View> implements SummaryContract.Presenter {
    private final StateManager stateManager;

    @Inject
    public SummaryPresenter(StateManager stateManger) {
        this.stateManager = stateManger;
    }

    @Override
    public void displayData() {
        final Summary summary = stateManager.getSummary();
        final List<Article> articles = stateManager.getLatestArticles();
        if(summary != null) {
            bindData(summary, articles);
        } else {
            view.onInternetConnectionError();
        }
    }

    @Override
    public void setSelectedCountry(Country country) {
        stateManager.setSelectedCountry(country);
    }

    private void bindData(Summary summary, List<Article> articleList){
        final StatisticSupplier supplier = new StatisticSupplier(summary);
        view.bindSummary(summary);
        view.bindLatestNews(articleList);
        view.bindTop5List(supplier.getTop5List());
    }

    public void displayInterstitialAd(Context context) {
        if(!stateManager.isAdLoaded()) {
            new InterstitialAdBuilder()
                    .setAdUnitId(Constants.TEST_AD_UNIT_ID)
                    .setAdRequest(new AdRequest.Builder().build())
                    .build(context)
                    .displayAd();
            stateManager.setAdLoaded(true);
        }
    }
}
