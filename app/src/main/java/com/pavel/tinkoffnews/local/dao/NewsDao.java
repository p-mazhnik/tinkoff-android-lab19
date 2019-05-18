package com.pavel.tinkoffnews.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.pavel.tinkoffnews.local.relation.TitleWithContent;
import com.pavel.tinkoffnews.model.Content;
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
    Flowable<List<Title>> getAllTitles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewsList(List<Title> news_list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTitleContent(Content content);

    @Transaction
    @Query("SELECT * FROM " + Title.TABLE_NAME +
            " INNER JOIN " + Content.TABLE_NAME +
            " ON " + Title.TABLE_NAME + "." + Title.COLUMN_ID + " = " + Content.TABLE_NAME + "." + Content.COLUMN_TITLE_ID +
            "  WHERE " + Content.COLUMN_TITLE_ID + " = :id")
    Flowable<List<TitleWithContent>> getTitleContentById(String id);

    @Update
    void updateNewsItem(Title title);
}
