package com.bilingoal.virustracker.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
    private final View layout;

    public RecyclerViewHeader(final Context context, RecyclerView parent, @LayoutRes int resId) {
        layout = LayoutInflater.from(context).inflate(resId, parent, false);
        layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        layout.layout(parent.getLeft(), 0, parent.getRight(), layout.getMeasuredHeight());
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(view) == 0) {
                canvas.save();
                final int height = layout.getMeasuredHeight();
                final int top = view.getTop() - height;
                canvas.translate(0, top);
                layout.draw(canvas);
                canvas.restore();
                break;
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0, layout.getMeasuredHeight(), 0, 0);
        } else {
            outRect.setEmpty();
        }
    }
}