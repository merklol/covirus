package com.bilingoal.virustracker.details;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.bilingoal.virustracker.R;
import com.bilingoal.virustracker.adapters.ListAdapter;
import com.bilingoal.virustracker.databinding.FragmentDetailsBinding;
import com.bilingoal.virustracker.dto.Country;
import com.bilingoal.virustracker.dto.Details;
import com.bilingoal.virustracker.graph.Configurator;
import com.bilingoal.virustracker.models.StateManager;
import com.github.mikephil.charting.data.Entry;
import dagger.android.support.DaggerFragment;

import javax.inject.Inject;
import java.util.List;

public class DetailsFragment extends DaggerFragment implements DetailsContract.View{
    @Inject
    StateManager stateManager;
    @Inject
    DetailsPresenter presenter;
    @Inject
    ListAdapter<Details> adapter;
    @Inject
    Configurator configurator;
    private FragmentDetailsBinding binding;

    public DetailsFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter.subscribe(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle("Details");
        final Country country = stateManager.getSelected();
        binding.totalCasesSubtitleView
                .setText(getString(R.string.total_cases_subtitle, stateManager.getSelected().getName()));
        configurator.configure(binding.lineChart);

        presenter.loadCases();
        presenter.displayData(country);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clearDisposables();
    }

    @Override
    public void displayCountryName(String countryName) {
        binding.countryNameView.setText(countryName);
    }

    @Override
    public void displayDate(String date) {
        binding.dateView.setText(date);
    }

    @Override
    public void displayDetails(List<Details> details) {
        adapter.setItems(details);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onParseDateError() {

    }

    @Override
    public void displayGraph(List<Entry> entries) {
        configurator.updateData(entries);
        binding.descriptionView.setVisibility(View.VISIBLE);
        binding.detailsProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onInternetConnectionError() {
        binding.lineChart.setNoDataText(getString(R.string.error_message));
        binding.lineChart.invalidate();
        binding.detailsProgressBar.setVisibility(View.GONE);
    }
}