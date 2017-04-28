package com.example.android.searchgit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.searchgit.R;
import com.example.android.searchgit.SearchGitApplication;
import com.example.android.searchgit.databinding.FragmentFeedBinding;
import com.example.android.searchgit.ui.common.AutoLoadingScrollListener;
import com.example.android.searchgit.ui.common.BaseFragment;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by yuanyou.
 */

public class FeedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGINATION_THRESHOLD = 5;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Disposable searchViewDisposal;
    private Disposable firstPageDisposal;
    private Disposable nextPageDisposal;

    private int loadedPageNum = 0;

    @Inject
    FeedViewModel viewModel;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchGitApplication.getApplication().getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflateAndDataBind(inflater, R.layout.fragment_feed, container, viewModel);
        setHasOptionsMenu(true);
        RecyclerView recyclerView = ((FragmentFeedBinding) binding).recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnScrollListener(onScrollListener);

        swipeRefreshLayout = ((FragmentFeedBinding) binding).swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setIconifiedByDefault(false);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        setupSearchView();

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onRefresh() {
        loadFirstPage(searchView.getQuery().toString());
    }

    private void setupSearchView() {
        if (searchViewDisposal != null && !searchViewDisposal.isDisposed()) {
            searchViewDisposal.dispose();
        }
        searchViewDisposal = RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(charSequence -> {
                    if (charSequence.length() >= 1) {
                        loadFirstPage(charSequence.toString());
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (searchViewDisposal != null) {
            searchViewDisposal.dispose();
        }
        if (firstPageDisposal != null) {
            firstPageDisposal.dispose();
        }
        if (nextPageDisposal != null) {
            nextPageDisposal.dispose();
        }
    }

    private void loadFirstPage(String query) {
        if (firstPageDisposal != null && !firstPageDisposal.isDisposed()) {
            firstPageDisposal.dispose();
        }
        firstPageDisposal = viewModel.getSearchResultFirstPage(query)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(
                        repos -> viewModel.replaceData(repos),
                        (x) -> {
                        },
                        () -> {
                            loadedPageNum = 1;
                            swipeRefreshLayout.setRefreshing(false);
                        }
                )
        ;
    }

    private void loadNextPage() {
        if (nextPageDisposal != null && !nextPageDisposal.isDisposed()) {
            nextPageDisposal.dispose();
        }
        nextPageDisposal = viewModel.getSearchResultNext(searchView.getQuery().toString(), loadedPageNum + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(repos -> viewModel.appendData(repos), (x) -> {
                }, () -> {
                    loadedPageNum++;
                    onScrollListener.onLoadingFinished();
                });
    }

    private final AutoLoadingScrollListener onScrollListener = new AutoLoadingScrollListener(PAGINATION_THRESHOLD) {

        @Override
        public void loadNextPage() {
            FeedFragment.this.loadNextPage();
        }
    };

}
