package com.bilingoal.virustracker.dto;

import com.bilingoal.virustracker.BuildConfig;

public class Version {
    private int latestVersionCode;
    private boolean versionChecked;

    public int getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(int latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public boolean isVersionChecked() {
        return versionChecked;
    }

    public void setVersionChecked(boolean versionChecked) {
        this.versionChecked = versionChecked;
    }

    public boolean isNewerVersionAvailable(){
        return this.latestVersionCode > BuildConfig.VERSION_CODE;
    }
}
