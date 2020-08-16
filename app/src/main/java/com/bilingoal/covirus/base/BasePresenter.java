package com.bilingoal.covirus.base;

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {
    protected T view;

    @Override
    public void subscribe(T view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }

    @Override
    public void clearDisposables() {

    }
}
