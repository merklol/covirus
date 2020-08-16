package com.bilingoal.covirus.adapters;

import android.widget.Filter;
import android.widget.Filterable;
import com.bilingoal.covirus.dto.Country;
import java9.util.stream.Collectors;
import java9.util.stream.StreamSupport;

import java.util.ArrayList;
import java.util.List;

public class FilterableCountryListAdapter extends ListAdapter<Country> implements Filterable {
    private List<Country> cached = new ArrayList<>();
    public FilterableCountryListAdapter(int layoutResId, boolean clickable) {
        super(layoutResId, clickable);
    }

    @Override
    public void setItems(List<Country> list) {
        super.setItems(list);
        this.cached = list;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Country> filtered;
            if(constraint.toString().isEmpty()) filtered = new ArrayList<>(cached);
            else
                filtered = StreamSupport.stream(cached).filter(country ->
                        country.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())
                ).collect(Collectors.toList());

            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Country> temp =  StreamSupport.stream((List<?>)results.values)
                    .filter(Country.class::isInstance)
                    .map(Country.class::cast)
                    .collect(Collectors.toList());

            list.clear();
            list.addAll(temp);
            notifyDataSetChanged();
        }
    };
}
