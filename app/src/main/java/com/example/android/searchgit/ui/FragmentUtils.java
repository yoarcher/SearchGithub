package com.example.android.searchgit.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by yuanyou.
 */

public class FragmentUtils {

    public static void replaceFragment(FragmentManager fragmentManager, int layoutId,
                                       @Nullable String tag, @NonNull Fragment fragment,
                                       boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(layoutId, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
