package com.bilingoal.virustracker.summary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import com.bilingoal.virustracker.R;
import com.bilingoal.virustracker.adapters.ListAdapter;
import com.bilingoal.virustracker.databinding.FragmentSummaryLayoutBinding;
import com.bilingoal.virustracker.dto.Article;
import com.bilingoal.virustracker.dto.Country;
import com.bilingoal.virustracker.dto.Summary;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;
import java.util.List;

public class SummaryFragment extends DaggerFragment implements SummaryContract.View {
    @Inject
    SummaryPresenter presenter;
    @Inject
    ListAdapter<Country> adapter;
    private FragmentSummaryLayoutBinding binding;

    @Inject
    public SummaryFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.subscribe(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary_layout, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.displayData();
        presenter.displayInterstitialAd(requireContext());
        adapter.setOnItemClickListener(onItemClickListener);
    }

    private final ListAdapter.OnItemClickListener<Country> onItemClickListener = item -> {
        presenter.setSelectedCountry(item);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_detailsFragment);
    };

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void bindSummary(Summary summary) {
        binding.setSummary(summary);
    }

    @Override
    public void bindTop5List(List<Country> countries) {
        adapter.setItems(countries);
        binding.top5ListView.setAdapter(adapter);
    }

    @Override
    public void bindLatestNews(List<Article> articles) {
        ListAdapter<Article> listAdapter = new ListAdapter<>(R.layout.artlicle_list_view_item, true);
        listAdapter.setItems(articles);
        binding.latestNewsListView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(item -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink())));
        });
    }

    @Override
    public void onInternetConnectionError() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Unknown Error")
                .setMessage("An unknown error has occurred.")
                .setPositiveButton("Ok", null)
                .show();
    }
}
