package com.bilingoal.virustracker.main;

import com.bilingoal.virustracker.base.BaseContract;

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
