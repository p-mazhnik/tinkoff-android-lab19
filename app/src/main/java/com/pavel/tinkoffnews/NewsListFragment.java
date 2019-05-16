package com.pavel.tinkoffnews;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pavel.tinkoffnews.databinding.NewsListFragmentBinding;
import com.pavel.tinkoffnews.remote.data.NewsListResponse;
import com.pavel.tinkoffnews.remote.data.PublicationDate;
import com.pavel.tinkoffnews.remote.data.Title;
import com.pavel.tinkoffnews.viewmodel.NewsViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

public class NewsListFragment extends Fragment {

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
//        List<Title> titles = new ArrayList<Title>();
//        titles.add(new Title("1", "ff", "ff", new PublicationDate(123), 213));
        mNewsListAdapter = new NewsListAdapter();
        recyclerView.setAdapter(mNewsListAdapter);
        return v;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mViewModel.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsListResponse>() {
                    @Override
                    public void accept(NewsListResponse response) throws Exception {
                        if (response != null && response.getResultCode().equals("OK")) {
                            List<Title> titles = response.getTitle();
                            Collections.sort(titles, new Comparator<Title>() {
                                @Override
                                public int compare(Title l, Title r) {
                                    long pb_date_1 = l.getPublicationDate().getMilliseconds();
                                    long pb_date_2 = r.getPublicationDate().getMilliseconds();
                                    return Long.compare(pb_date_2, pb_date_1);
                                }
                            });
                            mNewsListAdapter.setNewsList(titles);
                        } else {
                            Log.e("NewsListFragment", "response is null");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("NewsListFragment", "exception getting data: " + throwable.getMessage());
                    }
                });
    }

}
