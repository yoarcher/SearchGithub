package com.example.android.searchgit.ui.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.searchgit.BR;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by yuanyou.
 */

public class BaseFragment extends RxFragment {

    protected ViewDataBinding binding;

    protected View inflateAndDataBind(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup parent,
                                      @NonNull BaseViewModel viewModel) {
        binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }
}
