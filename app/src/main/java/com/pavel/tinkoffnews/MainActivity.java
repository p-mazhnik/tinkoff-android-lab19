package com.pavel.tinkoffnews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment);
        if(savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, getFragment()) //replace ?
                    .commit();
        }
    }

    protected Fragment getFragment() {
        return NewsListFragment.newInstance();
    }

    public void startFragmentById(long id) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putLong(NewsFragment.KEY_NEWS_ID, id);
        newsFragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container,
                        newsFragment, null).commit();
    }
}
