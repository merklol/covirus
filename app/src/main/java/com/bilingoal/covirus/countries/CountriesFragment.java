package com.bilingoal.covirus.countries;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import com.bilingoal.covirus.R;
import com.bilingoal.covirus.adapters.FilterableCountryListAdapter;
import com.bilingoal.covirus.adapters.ListAdapter;
import com.bilingoal.covirus.databinding.FragmentCountriesLayoutBinding;
import com.bilingoal.covirus.dto.Country;
import com.bilingoal.covirus.utils.RecyclerViewHeader;
import com.bilingoal.covirus.utils.SearchViewListener;
import dagger.android.support.DaggerFragment;

import javax.inject.Inject;
import java.util.*;

public class CountriesFragment extends DaggerFragment implements CountriesContract.View {
    @Inject
    FilterableCountryListAdapter adapter;
    @Inject
    CountriesPresenter presenter;
    private FragmentCountriesLayoutBinding binding;

    @Inject
    public CountriesFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.subscribe(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries_layout, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        presenter.createListOfCountries();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.countries_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(SearchViewListener.create(adapter));
    }

    @Override
    public void displayListOfCountries(List<Country> countries) {
        adapter.setItems(countries);
        adapter.setOnItemClickListener(onItemClickListener);
        binding.countriesListView.setAdapter(adapter);
        binding.countriesListView.addItemDecoration( new RecyclerViewHeader(requireContext(),
                binding.countriesListView, R.layout.countries_list_header));
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    private final ListAdapter.OnItemClickListener<Country> onItemClickListener = country -> {
        presenter.selectCountry(country);
        Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_detailsFragment);
    };

}
