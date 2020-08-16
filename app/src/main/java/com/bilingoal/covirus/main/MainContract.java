package com.bilingoal.covirus.main;

import com.bilingoal.covirus.base.BaseContract;

public interface MainContract extends BaseContract.Presenter<MainContract.View> {
    interface Presenter {
        void load();
        void checkAppVersion();
    }

    interface View extends BaseContract.View {
        void onInternetConnectionError();
        void onDataLoaded();
        void displayInfoMessage();
    }
}
