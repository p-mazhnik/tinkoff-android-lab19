package com.pavel.tinkoffnews.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pavel.tinkoffnews.local.entity.NewsEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by p.mazhnik on 16.05.2019.
 * to tinkoff-android-lab19
 */

@Dao
public interface NewsDao {
    @Query("SELECT * FROM " + NewsEntity.TABLE_NAME)
    Flowable<List<NewsEntity>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNewsList(List<NewsEntity> news_list);

    @Query("SELECT * FROM " + NewsEntity.TABLE_NAME + " WHERE " + NewsEntity.COLUMN_ID + " = :id")
    Flowable<NewsEntity> getNewsItemById(long id);
}
