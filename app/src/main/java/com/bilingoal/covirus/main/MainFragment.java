package com.bilingoal.covirus.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import com.bilingoal.covirus.R;
import com.bilingoal.covirus.adapters.ViewPagerAdapter;
import com.bilingoal.covirus.countries.CountriesFragment;
import com.bilingoal.covirus.databinding.FragmentMainBinding;
import com.bilingoal.covirus.summary.SummaryFragment;
import com.bilingoal.covirus.utils.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import dagger.android.support.DaggerFragment;

import javax.inject.Inject;

public class MainFragment extends DaggerFragment implements MainContract.View {
    @Inject
    SummaryFragment summaryFragment;
    @Inject
    CountriesFragment countriesFragment;
    @Inject
    MainPresenter presenter;
    private FragmentMainBinding binding;

    public MainFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.subscribe(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding.errorPage.errorView.setVisibility(View.GONE);
        enableBottomBar(false);
        setupInfoMessage();

        presenter.load();
        presenter.checkAppVersion();

        binding.bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        binding.errorPage.retryBtn.setOnClickListener(onRetryBtnClickListener);
    }

    @Override
    public void onInternetConnectionError() {
        displayErrorPage();
    }

    @Override
    public void onDataLoaded() {
        enableBottomBar(true);
        setupViewPager();
        hideProgressBar();
    }

    @Override
    public void displayInfoMessage() {
        binding.messageInfo.messageInfoRoot.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clearDisposables();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.settings) {
            Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_settingsFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
    }

    private void displayErrorPage(){
        binding.errorPage.errorView.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void displayProgressBar(){
        binding.errorPage.errorView.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void enableBottomBar(boolean state){
        for (int i = 0; i < binding.bottomNavigation.getMenu().size(); i++) {
            binding.bottomNavigation.getMenu().getItem(i).setEnabled(state);
        }
    }

    private void setupViewPager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.notifyDataSetChanged();
        adapter.addFragment(new SummaryFragment());
        adapter.addFragment(new CountriesFragment());
        binding.viewpager.setAdapter(adapter);
    }

    private void setupInfoMessage() {
        binding.messageInfo.messageInfoButton.setOnClickListener(onClickListener);
        binding.messageInfo.messageInfoRoot.bringToFront();
        binding.messageInfo.messageInfoRoot.setVisibility(View.GONE);
    }

    private final View.OnClickListener onClickListener = v -> {
        startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(Constants.PLAY_MARKET_LINK))
        );
    };

    private final BottomNavigationView.OnNavigationItemSelectedListener
            onNavigationItemSelectedListener = item -> {
        final int selected = item.getItemId() == R.id.global ? 0 : 1;
        binding.viewpager.setCurrentItem(selected);
        return true;
    };

    private final View.OnClickListener onRetryBtnClickListener = v -> {
        presenter.load();
        displayProgressBar();
    };
}
