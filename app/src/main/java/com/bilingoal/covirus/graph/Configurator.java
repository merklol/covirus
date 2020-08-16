package com.bilingoal.covirus.graph;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.List;

public interface Configurator {
    void configure(LineChart lineChart);
    void updateData(List<Entry> entries);
}
