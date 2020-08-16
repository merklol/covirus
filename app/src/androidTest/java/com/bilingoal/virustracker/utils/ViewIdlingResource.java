package com.bilingoal.virustracker.utils;

import android.view.View;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewFinder;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Matcher;
import java.lang.reflect.Field;
import static androidx.test.espresso.Espresso.onView;


public class ViewIdlingResource implements IdlingResource {

    private final Matcher<View> viewMatcher;
    private final Matcher<View> idleMatcher;
    private ResourceCallback resourceCallback;

    public ViewIdlingResource(final Matcher<View> viewMatcher, Matcher<View> idlerMatcher) {
        this.viewMatcher = viewMatcher;
        this.idleMatcher = idlerMatcher;
    }

    @Override
    public boolean isIdleNow() {
        View view = getView(viewMatcher);
        boolean isIdle = idleMatcher.matches(view);

        if (isIdle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }

        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    @Override
    public String getName() {
        return this + viewMatcher.toString();
    }

    private static View getView(Matcher<View> viewMatcher) {
        try {
            ViewInteraction viewInteraction = onView(viewMatcher);
            Field finderField = viewInteraction.getClass().getDeclaredField("viewFinder");
            finderField.setAccessible(true);
            ViewFinder finder = (ViewFinder) finderField.get(viewInteraction);
            return finder.getView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}