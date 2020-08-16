package com.bilingoal.covirus.di.modules;

import android.app.Application;
import android.content.Context;
import androidx.core.content.ContextCompat;
import com.bilingoal.covirus.R;
import com.bilingoal.covirus.adapters.ListAdapter;
import com.bilingoal.covirus.details.DetailsPresenter;
import com.bilingoal.covirus.di.qualifiers.DetailsList;
import com.bilingoal.covirus.di.scopes.ActivityScope;
import com.bilingoal.covirus.dto.Details;
import com.bilingoal.covirus.graph.Configurator;
import com.bilingoal.covirus.graph.LineChartConfigurator;
import com.bilingoal.covirus.models.StateManager;
import com.bilingoal.covirus.network.NetworkInterface;
import com.bilingoal.covirus.utils.schedulerproviders.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {

    @ActivityScope
    @Provides
    static DetailsPresenter provideDetailsPresenter(NetworkInterface networkInterface, StateManager stateManager,
                                                    SchedulerProvider schedulerProvider){
        return new DetailsPresenter(networkInterface, stateManager, schedulerProvider);
    }

    @ActivityScope
    @DetailsList
    @Provides
    static int provideRecyclerViewLayoutResId(){
        return R.layout.list_view_item;
    }

    @ActivityScope
    @Provides
    static ListAdapter<Details> provideRecyclerViewAdapter(@DetailsList int layoutResId){
        return new ListAdapter<>(layoutResId, false);
    }

    @ActivityScope
    @Provides
    static Configurator provideLineChartConfigurator(Application application){
        Context context = application.getApplicationContext();
        return new LineChartConfigurator.Builder()
                .setGraphColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setMarkerView(context, R.layout.marker_view_layout, R.id.value_view)
                .setNoDataText(context.getString(R.string.no_data_text))
                .setNoDataTextColor(ContextCompat.getColor(context, R.color.subtitleColor))
                .createConfigurator();
    }
}
