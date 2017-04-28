package com.example.android.searchgit.ui.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.searchgit.BR;

import java.util.List;

/**
 * Created by yuanyou.
 */

public abstract class VerticalListAdapter<T> extends RecyclerView.Adapter<VerticalListAdapter.ItemViewHolder<T>> {

    protected List<T> items;
    private LayoutInflater inflater;

    public VerticalListAdapter(@NonNull List<T> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, getLayoutRes(viewType), parent, false);
        return new ItemViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<T> holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @LayoutRes
    protected abstract int getLayoutRes(int viewType);

    public void replaceData(List<T> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void appendData(List<T> newItems) {
        int rangeStart = items.size();
        items.addAll(newItems);
        notifyItemRangeInserted(rangeStart, newItems.size());
    }

    public static class ItemViewHolder<T> extends RecyclerView.ViewHolder {

        protected final ViewDataBinding binding;

        public ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(T item) {
            binding.setVariable(BR.item, item);
            binding.executePendingBindings();
        }

    }
}
