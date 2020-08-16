package com.bilingoal.virustracker.utils;

import androidx.appcompat.widget.SearchView;
import com.bilingoal.virustracker.adapters.FilterableCountryListAdapter;

public class SearchViewListener implements SearchView.OnQueryTextListener {
    private final FilterableCountryListAdapter adapter;

    public SearchViewListener(FilterableCountryListAdapter adapter) {
        this.adapter = adapter;
    }

    public static SearchViewListener create(FilterableCountryListAdapter adapter) {
        return new SearchViewListener(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }
}