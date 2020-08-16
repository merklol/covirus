package com.bilingoal.virustracker.graph;

import android.content.Context;
import android.graphics.Color;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public final class LineChartConfigurator implements Configurator {
    private LineChart lineChart;
    private final CustomMarkerView markerView;
    private final int graphColor;
    private final String noDataText;
    private final int noDataTextColor;
    private final List<Entry> entries = new ArrayList<>();

    private LineChartConfigurator(CustomMarkerView markerView,
                                  int graphColor, String noDataText, int noDataTextColor) {
        this.markerView = markerView;
        this.graphColor = graphColor;
        this.noDataText = noDataText;
        this.noDataTextColor = noDataTextColor;
    }

    @Override
    public void configure(LineChart lineChart) {
        this.lineChart = lineChart;
        styleGraph();
        lineChart.setMarker(markerView);
        lineChart.setHighlightPerTapEnabled(true);
    }

    @Override
    public void updateData(List<Entry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
        LineData lineData = new LineData(createDataSet());
        lineChart.setData(lineData);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    private LineDataSet createDataSet() {
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setLineWidth(3f);
        lineDataSet.setCircleRadius(6f);
        lineDataSet.setColor(graphColor);
        lineDataSet.setCircleHoleRadius(3f);
        lineDataSet.setCircleColor(graphColor);
        lineDataSet.setValueTextSize(14f);
        lineDataSet.setDrawValues(false);
        lineDataSet.setHighlightEnabled(true);
        return lineDataSet;
    }

    private void styleGraph() {
        lineChart.setNoDataTextColor(Color.BLACK);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        lineChart.getXAxis().setAvoidFirstLastClipping(true);
        lineChart.getDescription().setEnabled(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setNoDataTextColor(noDataTextColor);
        lineChart.setNoDataText(noDataText);
    }

    public static class Builder {
        private int graphColor;
        private String noDataText;
        private int noDataTextColor;
        private CustomMarkerView markerView;

        public Builder setGraphColor(int color) {
            graphColor = color;
            return this;
        }

        public Builder setNoDataText(String text) {
            noDataText = text;
            return this;
        }

        public Builder setNoDataTextColor(int color) {
            noDataTextColor = color;
            return this;
        }

        public Builder setMarkerView(Context context, int layoutId, int valueId) {
            markerView = new CustomMarkerView(context, layoutId, valueId);
            return this;
        }

        public LineChartConfigurator createConfigurator() {
            return new LineChartConfigurator(markerView, graphColor, noDataText, noDataTextColor);
        }
    }
}
