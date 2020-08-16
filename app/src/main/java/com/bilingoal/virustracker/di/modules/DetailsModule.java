package com.bilingoal.virustracker.di.modules;

import android.app.Application;
import android.content.Context;
import androidx.core.content.ContextCompat;
import com.bilingoal.virustracker.R;
import com.bilingoal.virustracker.adapters.ListAdapter;
import com.bilingoal.virustracker.details.DetailsPresenter;
import com.bilingoal.virustracker.di.qualifiers.DetailsList;
import com.bilingoal.virustracker.di.scopes.ActivityScope;
import com.bilingoal.virustracker.dto.Details;
import com.bilingoal.virustracker.graph.Configurator;
import com.bilingoal.virustracker.graph.LineChartConfigurator;
import com.bilingoal.virustracker.models.StateManager;
import com.bilingoal.virustracker.network.NetworkInterface;
import com.bilingoal.virustracker.utils.schedulerproviders.SchedulerProvider;
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
