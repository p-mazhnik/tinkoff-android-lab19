package com.pavel.tinkoffnews.local;

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
        return mDatabase.mNewsDao().getAllNews();
    }

    public void insertNewsList(final List<Title> news_list){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.mNewsDao().insertNewsList(news_list);
            }
        });
    }

    public Flowable<Title> getNewsItemById(long id){
        return mDatabase.mNewsDao().getNewsItemById(id);
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
