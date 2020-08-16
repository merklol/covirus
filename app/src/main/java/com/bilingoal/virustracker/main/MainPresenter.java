package com.bilingoal.virustracker.main;

import com.bilingoal.virustracker.base.BasePresenter;
import com.bilingoal.virustracker.dto.Response;
import com.bilingoal.virustracker.dto.Summary;
import com.bilingoal.virustracker.dto.Version;
import com.bilingoal.virustracker.models.VersionLoader;
import com.bilingoal.virustracker.models.loaders.*;
import com.bilingoal.virustracker.network.NetworkInterface;
import com.bilingoal.virustracker.models.StateManager;
import com.bilingoal.virustracker.utils.Constants;
import com.bilingoal.virustracker.utils.schedulerproviders.SchedulerProvider;
import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private final NetworkInterface networkInterface;
    private final SchedulerProvider schedulerProvider;
    private final StateManager stateManager;
    private final VersionLoader versionLoader;
    private Version version;
    private Loader summaryLoader;
    private Loader articlesLoader;

    @Inject
    public MainPresenter(NetworkInterface networkInterface, SchedulerProvider schedulerProvider,
                                StateManager stateManger, VersionLoader versionLoader) {
        this.networkInterface = networkInterface;
        this.schedulerProvider = schedulerProvider;
        this.stateManager = stateManger;
        this.versionLoader = versionLoader;
        this.version =  stateManager.getVersion();

        createChain();
        setLoadersListeners();
    }

    @Override
    public void load() {
        final Summary summary = stateManager.getSummary();

        if(summary == null) {
            summaryLoader.load(Response.createDefaultResponse());
        } else {
            view.onDataLoaded();
        }
    }

    @Override
    public void checkAppVersion(){
        versionLoader.setListener(listener);
        if(!version.isVersionChecked()) {
            versionLoader.getLatestVersionCode();
        } else {
            displayInfoMessage();
        }
    }

    @Override
    public void clearDisposables() {
        ((SummaryLoader)summaryLoader).clearDisposables();
    }

    private void displayInfoMessage() {
        if(version.isNewerVersionAvailable()) view.displayInfoMessage();
    }

    private void createChain() {
        summaryLoader = new SummaryLoader(networkInterface, schedulerProvider);
        articlesLoader = new ArticlesLoader(Constants.ARTICLES_COLLECTION);
        summaryLoader.then(articlesLoader);
    }

    private final VersionLoader.OnNewerVersionAvailableListener listener = versionCode -> {
        version.setLatestVersionCode(versionCode);
        version.setVersionChecked(true);
        displayInfoMessage();
    };

    private void setLoadersListeners() {
        summaryLoader.setFailureListener(() -> view.onInternetConnectionError());
        articlesLoader.setFailureListener(() -> view.onInternetConnectionError());

        articlesLoader.setLoadedListener(response -> {
            stateManager.setSummary(response.getSummary());
            stateManager.setLatestArticles(response.getArticleList());
            view.onDataLoaded();
        });
    }
}
