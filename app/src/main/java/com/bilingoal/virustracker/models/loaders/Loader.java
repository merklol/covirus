package com.bilingoal.virustracker.models.loaders;

import com.bilingoal.virustracker.dto.Response;

public abstract class Loader {
    protected Loader nextLoader;
    protected OnLoadedListener loadedListener;
    protected OnFailureListener failureListener;

    public void then(Loader nextLoader){
        this.nextLoader = nextLoader;
    }

    public abstract void load(Response response);

    protected void next(Response response) {
        if(nextLoader == null) return;

        nextLoader.load(response);
    }

    public void setLoadedListener(OnLoadedListener loadedListener) {
        this.loadedListener = loadedListener;
    }

    public void setFailureListener(OnFailureListener failureListener) {
        this.failureListener = failureListener;
    }
}
