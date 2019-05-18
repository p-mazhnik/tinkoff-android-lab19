package com.pavel.tinkoffnews.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pavel.tinkoffnews.model.Title;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by p.mazhnik on 16.05.2019.
 * to tinkoff-android-lab19
 */

@Dao
public interface NewsDao {
    @Query("SELECT * FROM " + Title.TABLE_NAME)
    Flowable<List<Title>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNewsList(List<Title> news_list);

    @Query("SELECT * FROM " + Title.TABLE_NAME + " WHERE " + Title.COLUMN_ID + " = :id")
    Flowable<Title> getNewsItemById(long id);
}
