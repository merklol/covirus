package com.bilingoal.covirus.models;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialAdBuilder {
    private InterstitialAd interstitialAd;
    private String adUnitId;
    private AdRequest adRequest;

    public InterstitialAdBuilder build(Context context) {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(adUnitId);
        return this;
    }

    public InterstitialAdBuilder setAdUnitId(String adUnitId) {
       this.adUnitId = adUnitId;
       return this;
    }

    public InterstitialAdBuilder setAdRequest(AdRequest adRequest) {
        this.adRequest = adRequest;
        return this;
    }

    public void displayAd() {
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new Listener());
    }

    private class Listener extends AdListener {
        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
            interstitialAd.show();
        }
    }
}
