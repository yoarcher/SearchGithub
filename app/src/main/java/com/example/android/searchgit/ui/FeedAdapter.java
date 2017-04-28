package com.example.android.searchgit.ui;

import com.example.android.searchgit.R;
import com.example.android.searchgit.data.model.Repo;
import com.example.android.searchgit.ui.common.VerticalListAdapter;

import java.util.ArrayList;

/**
 * Created by yuanyou.
 */

public class FeedAdapter extends VerticalListAdapter<Repo> {

    public FeedAdapter() {
        super(new ArrayList<Repo>());
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.item_repo;
    }
}
