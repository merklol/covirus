package com.bilingoal.virustracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter<T> extends RecyclerView.Adapter<ListAdapter<T>.ViewHolder> {
    protected List<T> list;
    private OnItemClickListener<T> clickListener;
    private final int layoutResId;
    private final boolean clickable;

    @Inject
    public ListAdapter(int layoutResId, boolean clickable) {
        this.layoutResId = layoutResId;
        this.clickable = clickable;
    }

    public interface OnItemClickListener<T> {
        void onClick(T item);
    }

    public void setOnItemClickListener(OnItemClickListener<T> clickListener) {
        this.clickListener = clickListener;
    }

    public void setItems(List<T> list){
        this.list = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutResId, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ViewDataBinding binding;

        public ViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        public void bind(T item){
            this.binding.setVariable(BR.item, item);
            this.binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            if(clickable){
                clickListener.onClick(list.get(getAdapterPosition()));
            }
        }
    }
}
