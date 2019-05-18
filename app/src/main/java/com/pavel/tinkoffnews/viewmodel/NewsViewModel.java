package com.pavel.tinkoffnews.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.pavel.tinkoffnews.BasicApp;
import com.pavel.tinkoffnews.local.LocalRepository;
import com.pavel.tinkoffnews.local.relation.TitleWithContent;
import com.pavel.tinkoffnews.model.Content;
import com.pavel.tinkoffnews.remote.RemoteRepository;
import com.pavel.tinkoffnews.remote.data.NewsListResponse;
import com.pavel.tinkoffnews.remote.data.NewsItemResponse;
import com.pavel.tinkoffnews.model.Title;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */
public class NewsViewModel extends AndroidViewModel {

    private RemoteRepository mRemoteRepository;
    private LocalRepository mLocalRepository;

    public NewsViewModel(Application application) {
        super(application);
        this.mRemoteRepository = ((BasicApp) application).getRemoteRepository();
        this.mLocalRepository = ((BasicApp) application).getLocalRepository();
    }

    public Flowable<NewsListResponse> getRemoteNews(){
        return mRemoteRepository.getNews();
    }

    public Flowable<NewsItemResponse> getRemoteNewsContentById(String id){
        return mRemoteRepository.getNewsContentById(id);
    }

    public Flowable<List<Title>> getLocalNews(){
        return mLocalRepository.getAllNews();
    }

    public void insertNewsList(List<Title> news_list){
        mLocalRepository.insertNewsList(news_list);
    }

    public void insertTitleContent(Content content){
        mLocalRepository.insertTitleContent(content);
    }

    public Flowable<List<TitleWithContent>> getLocalTitleContentById(String id){
        return mLocalRepository.getTitleContentById(id);
    }

    public void updateNewsItem(final Title title){
        mLocalRepository.updateNewsItem(title);
    }
}
