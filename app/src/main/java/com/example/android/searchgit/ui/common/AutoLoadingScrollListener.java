package com.example.android.searchgit.ui.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by yuanyou.
 */

public abstract class AutoLoadingScrollListener extends RecyclerView.OnScrollListener {

    private final int paginationThreshold;
    private AtomicBoolean isLoading = new AtomicBoolean(false);

    public AutoLoadingScrollListener(int paginationThreshold) {
        this.paginationThreshold = paginationThreshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy <= 0 || isLoading.get()) {
            return;
        }
        if (canLoadMore(recyclerView)) {
            isLoading.set(true);
            loadNextPage();
        }
    }

    private boolean canLoadMore(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return layoutManager.getItemCount() <= layoutManager.findLastVisibleItemPosition() + paginationThreshold;
    }

    public void onLoadingFinished() {
        isLoading.set(false);
    }

    public abstract void loadNextPage();
}
