package com.bilingoal.virustracker.summary;

import com.bilingoal.virustracker.base.BaseContract;
import com.bilingoal.virustracker.dto.Article;
import com.bilingoal.virustracker.dto.Country;
import com.bilingoal.virustracker.dto.Summary;

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
