package com.pavel.tinkoffnews;

import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pavel.tinkoffnews.remote.data.NewsListResponse;
import com.pavel.tinkoffnews.model.Title;
import com.pavel.tinkoffnews.viewmodel.NewsViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsViewModel mViewModel;
    private NewsListAdapter mNewsListAdapter;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_list_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.news_list);
        mNewsListAdapter = new NewsListAdapter();
        recyclerView.setAdapter(mNewsListAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.news_list_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        return v;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mViewModel.getLocalNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Title>>() {
                    @Override
                    public void accept(List<Title> response) throws Exception {
                        if (response != null && !response.isEmpty()) {
                            Collections.sort(response, new Comparator<Title>() {
                                @Override
                                public int compare(Title l, Title r) {
                                    long pb_date_1 = l.getPublicationDate().getMilliseconds();
                                    long pb_date_2 = r.getPublicationDate().getMilliseconds();
                                    return Long.compare(pb_date_2, pb_date_1);
                                }
                            });
                            mNewsListAdapter.setNewsList(response);
                            mSwipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.e("NewsListFragment", "response is null");

                            mSwipeRefreshLayout.post(new Runnable() {

                                @Override
                                public void run() {
                                    // Fetching data from server
                                    load_remote_data();
                                }
                            });
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("NewsListFragment", "exception getting data: " + throwable.getMessage());
                    }
                });
    }

    @Override
    public void onRefresh() {
        // Fetching data from server
        load_remote_data();
    }

    @SuppressLint("CheckResult")
    private void load_remote_data(){
        mSwipeRefreshLayout.setRefreshing(true);
        mViewModel.getRemoteNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsListResponse>() {
                    @Override
                    public void accept(NewsListResponse response) throws Exception {
                        if (response != null && response.getResultCode().equals("OK")) {
                            List<Title> titles = response.getTitle();
                            mViewModel.insertNewsList(titles);
                        } else {
                            Log.e("NewsListFragment", "response is null");
                            mSwipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("NewsListFragment", "exception insert data: " + throwable.getMessage());
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
