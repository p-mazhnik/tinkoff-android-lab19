package com.pavel.tinkoffnews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.pavel.tinkoffnews.remote.data.NewsItemResponse;
import com.pavel.tinkoffnews.remote.data.NewsListResponse;
import com.pavel.tinkoffnews.remote.data.Payload;
import com.pavel.tinkoffnews.remote.data.Title;
import com.pavel.tinkoffnews.viewmodel.NewsViewModel;

import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by p.mazhnik on 16.05.2019.
 * to tinkoff-android-lab19
 */
public class NewsFragment extends Fragment {
    public static final String KEY_NEWS_ID = "news_id";

    private long news_id;
    private NewsViewModel mViewModel;
    private NewsListAdapter mNewsListAdapter;

    private TextView news_title;
    private TextView news_creationDate;
    private TextView news_content;

    private View mView;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.news_fragment, container, false);
        if (getArguments() != null) {
            this.news_id = getArguments().getLong(KEY_NEWS_ID);
        }
        return mView;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        news_title = mView.findViewById(R.id.news_title);
        news_content = mView.findViewById(R.id.news_content);
        news_creationDate = mView.findViewById(R.id.news_creationDate);

        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mViewModel.getNewsContentById(String.valueOf(this.news_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsItemResponse>() {
                    @Override
                    public void accept(NewsItemResponse response) throws Exception {
                        if (response != null && response.getResultCode().equals("OK")) {
                            Payload news_info = response.getPayload();
                            news_title.setText(news_info.getTitle().getText());
                            news_content.setText(Html.fromHtml(news_info.getContent()));
                            news_creationDate.setText(String.valueOf(news_info.getCreationDate().getMilliseconds()));
                        } else {
                            Log.e("NewsFragment", "response is null");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("NewsFragment", "exception getting data: " + throwable.getMessage());
                    }
                });
    }
}
