package com.pavel.tinkoffnews.remote;

import com.pavel.tinkoffnews.remote.data.NewsListResponse;
import com.pavel.tinkoffnews.remote.data.NewsItemResponse;

import io.reactivex.Flowable;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */
public class RemoteRepository {
    private static volatile RemoteRepository instance;

    private TinkoffAPI mTinkoffClient;


    public RemoteRepository(TinkoffAPI tinkoffClient) {
        this.mTinkoffClient = tinkoffClient;
    }

    public static RemoteRepository getInstance(TinkoffAPI tinkoffClient) {
        if (instance == null) {
            synchronized (RemoteRepository.class) {
                if (instance == null) {
                    instance = new RemoteRepository(tinkoffClient);
                }
            }
        }
        return instance;
    }

    public Flowable<NewsListResponse> getNews(){
        return mTinkoffClient.getNews();
    }

    public Flowable<NewsItemResponse> getNewsContentById(String id){
        return mTinkoffClient.getNewsContentById(id);
    }
}
