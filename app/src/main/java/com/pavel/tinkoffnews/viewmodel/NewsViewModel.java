package com.pavel.tinkoffnews.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.pavel.tinkoffnews.BasicApp;
import com.pavel.tinkoffnews.remote.RemoteRepository;
import com.pavel.tinkoffnews.remote.data.NewsListResponse;
import com.pavel.tinkoffnews.remote.data.NewsItemResponse;

import io.reactivex.Flowable;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */
public class NewsViewModel extends AndroidViewModel {

    private RemoteRepository mRemoteRepository;

    public NewsViewModel(Application application) {
        super(application);
        this.mRemoteRepository = ((BasicApp) application).getRemoteRepository();
    }

    public Flowable<NewsListResponse> getNews(){
        return mRemoteRepository.getNews();
    }

    public Flowable<NewsItemResponse> getNewsContentById(String id){
        return mRemoteRepository.getNewsContentById(id);
    }
}
