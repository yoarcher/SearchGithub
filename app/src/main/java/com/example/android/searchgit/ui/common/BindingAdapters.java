package com.example.android.searchgit.ui.common;

import android.databinding.BindingAdapter;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.searchgit.SearchGitApplication;
import com.example.android.searchgit.data.model.Actor;

/**
 * Created by yuanyou.
 */

public class BindingAdapters {

    @BindingAdapter("adapter")
    public static <T> void setAdapter(RecyclerView recyclerView, VerticalListAdapter<T> adapter) {
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("actor_image")
    public static void setActorImage(ImageView imageView, Actor actor) {
        Log.d("Activity", imageView.getContext().toString());
        // TODO: Memory leak!! Replace following with activity context once orientation crash is fixed.
        Glide.with(SearchGitApplication.getApplication())
                .load(actor.getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(imageView);
    }
}
