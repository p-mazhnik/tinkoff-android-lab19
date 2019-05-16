package com.pavel.tinkoffnews;

import android.app.Application;

import com.pavel.tinkoffnews.local.AppDatabase;
import com.pavel.tinkoffnews.local.LocalRepository;
import com.pavel.tinkoffnews.remote.RemoteRepository;
import com.pavel.tinkoffnews.remote.TinkoffAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */

public class BasicApp extends Application {
    public static final String TINKOFF_API_URL = "https://api.tinkoff.ru/v1/";

    private Retrofit mRetrofit;
    private TinkoffAPI mTinkoffAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(TINKOFF_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mTinkoffAPI = mRetrofit.create(TinkoffAPI.class);
    }

    public Retrofit getRemoteClient() {
        return this.mRetrofit;
    }

    public TinkoffAPI getTinkoffAPI() {
        return mTinkoffAPI;
    }

    public RemoteRepository getRemoteRepository() {
        return RemoteRepository.getInstance(getTinkoffAPI());
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public LocalRepository getLocalRepository() {
        return LocalRepository.getInstance(getDatabase());
    }
}
