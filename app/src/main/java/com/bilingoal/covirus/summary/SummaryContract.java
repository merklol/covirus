package com.bilingoal.covirus.summary;

import com.bilingoal.covirus.base.BaseContract;
import com.bilingoal.covirus.dto.Article;
import com.bilingoal.covirus.dto.Country;
import com.bilingoal.covirus.dto.Summary;

import java.util.List;

public interface SummaryContract {

    interface Presenter extends BaseContract.Presenter<SummaryContract.View> {
        void displayData();
        void setSelectedCountry(Country country);
    }

    interface View extends BaseContract.View {
        void bindSummary(Summary summary);
        void bindTop5List(List<Country> countries);
        void bindLatestNews(List<Article> articles);
        void onInternetConnectionError();
    }
}
