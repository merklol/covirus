package com.bilingoal.virustracker.graph;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

@SuppressLint("ViewConstructor")
public class CustomMarkerView extends MarkerView {
    private final TextView valueView;

    public CustomMarkerView(Context context, int layoutResource, int viewResource) {
        super(context, layoutResource);
        valueView = findViewById(viewResource);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        valueView.setText(String.valueOf((int)e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        return new MPPointF(-getWidth() / 2f, -getHeight());
    }
}
