package com.bilingoal.virustracker.base;

public interface BaseContract {
    interface Presenter<T extends View> {
        void subscribe(T view);
        void unsubscribe();
        void clearDisposables();
    }

    interface View { }
}
