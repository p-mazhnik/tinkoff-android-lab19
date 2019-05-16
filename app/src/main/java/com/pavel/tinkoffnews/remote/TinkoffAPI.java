package com.pavel.tinkoffnews.remote;

import com.pavel.tinkoffnews.remote.data.NewsListResponse;
import com.pavel.tinkoffnews.remote.data.NewsItemResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */
public interface TinkoffAPI {
    @GET("news/")
    Flowable<NewsListResponse> getNews();

    @GET("news_content?id={id}")
    Flowable<NewsItemResponse> getNewsContentById(@Path("id") String id);
}
