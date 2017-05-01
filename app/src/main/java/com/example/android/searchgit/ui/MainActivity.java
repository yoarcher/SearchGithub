package com.example.android.searchgit.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.searchgit.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static int NUM_FRAGMENT = 1;
    private final static String TAG_FEED_FRAGMENT = "TAG_FEED_FRAGMENT";

    private List<Fragment> fragments = new ArrayList<>(NUM_FRAGMENT);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                // NOOP
                return true;
            case R.id.navigation_dashboard:
                // NOOP
                return true;
            case R.id.navigation_notifications:
                // NOOP
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Activity", this.toString());
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initFragments();
    }

    private void initFragments() {
        Fragment feedFragment = getSupportFragmentManager().findFragmentByTag(TAG_FEED_FRAGMENT);
        if (feedFragment == null) {
            feedFragment = FeedFragment.newInstance();
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.content,
                    TAG_FEED_FRAGMENT, feedFragment, false);
        }
        fragments.add(feedFragment);
    }

}
