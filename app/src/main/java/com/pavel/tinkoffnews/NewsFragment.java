package com.pavel.tinkoffnews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pavel.tinkoffnews.local.relation.TitleWithContent;
import com.pavel.tinkoffnews.model.Content;
import com.pavel.tinkoffnews.model.Title;
import com.pavel.tinkoffnews.remote.data.NewsItemResponse;
import com.pavel.tinkoffnews.remote.data.Payload;
import com.pavel.tinkoffnews.viewmodel.NewsViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by p.mazhnik on 16.05.2019.
 * to tinkoff-android-lab19
 */
public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String KEY_NEWS_ID = "news_id";

    private long news_id;
    private NewsViewModel mViewModel;
    private NewsListAdapter mNewsListAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mNewsTitle;
    private TextView mNewsPublicationDate;
    private TextView mNewsContent;

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
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.news_item_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        return mView;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mNewsTitle = mView.findViewById(R.id.news_title);
        mNewsContent = mView.findViewById(R.id.news_content);
        mNewsPublicationDate = mView.findViewById(R.id.news_creationDate);

        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mViewModel.getLocalTitleContentById(String.valueOf(this.news_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TitleWithContent>>() {
                    @Override
                    public void accept(List<TitleWithContent> response) throws Exception {
                        if (response != null && !response.isEmpty()) {
                            TitleWithContent title = response.get(0);
                            mNewsTitle.setText(Html.fromHtml(title.getTitle().getText()));
                            mNewsContent.setText(Html.fromHtml(title.getContent().getContent()));
                            mNewsPublicationDate.setText(getFormattedDateFromTimestamp(title.getTitle()
                                    .getPublicationDate().getMilliseconds()));
                            mSwipeRefreshLayout.setRefreshing(false);
                        } else {
                            Log.e("NewsFragment", "title or content is null");
                            mSwipeRefreshLayout.post(new Runnable() {

                                @Override
                                public void run() {
                                    load_remote_data();
                                }
                            });
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("NewsFragment", "exception getting local data: " + throwable.getMessage());
                    }
                });
    }

    public static String getFormattedDateFromTimestamp(long timestampInMilliSeconds)
    {
        Date date = new Date();
        date.setTime(timestampInMilliSeconds);
        return new SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date);
    }

    @Override
    public void onRefresh() {
        load_remote_data();
    }

    @SuppressLint("CheckResult")
    private void load_remote_data(){
        mSwipeRefreshLayout.setRefreshing(true);
        mViewModel.getRemoteNewsContentById(String.valueOf(this.news_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsItemResponse>() {
                    @Override
                    public void accept(NewsItemResponse response) throws Exception {
                        if (response != null && response.getResultCode().equals("OK")) {
                            Payload news_info = response.getPayload();
                            Title title = news_info.getTitle();
                            mViewModel.insertTitleContent(new Content(title.getId(), news_info.getContent()));
                        } else {
                            Log.e("NewsFragment", "response is null");
                            mSwipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("NewsFragment", "exception getting data: " + throwable.getMessage());
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
