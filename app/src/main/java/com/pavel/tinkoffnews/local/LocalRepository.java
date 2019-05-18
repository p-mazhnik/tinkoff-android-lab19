package com.pavel.tinkoffnews.local;

import com.pavel.tinkoffnews.local.relation.TitleWithContent;
import com.pavel.tinkoffnews.model.Content;
import com.pavel.tinkoffnews.model.Title;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Flowable;

/**
 * Created by p.mazhnik on 16.05.2019.
 * to tinkoff-android-lab19
 */
public class LocalRepository {
    private static volatile LocalRepository instance;

    private AppDatabase mDatabase;
    private final Executor mExecutor = Executors.newFixedThreadPool(2);

    public Flowable<List<Title>> getAllNews(){
        return mDatabase.mNewsDao().getAllTitles();
    }

    public void insertNewsList(final List<Title> news_list){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.mNewsDao().insertNewsList(news_list);
            }
        });
    }

    public void insertTitleContent(final Content content){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.mNewsDao().insertTitleContent(content);
            }
        });
    }

    public Flowable<List<TitleWithContent>> getTitleContentById(String id){
        return mDatabase.mNewsDao().getTitleContentById(id);
    }

    public void updateNewsItem(final Title title){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.mNewsDao().updateNewsItem(title);
            }
        });
    }

    private LocalRepository(AppDatabase database) {
        this.mDatabase = database;
    }

    public static LocalRepository getInstance(AppDatabase database) {
        if (instance == null) {
            synchronized (LocalRepository.class) {
                if (instance == null) {
                    instance = new LocalRepository(database);
                }
            }
        }
        return instance;
    }
}
