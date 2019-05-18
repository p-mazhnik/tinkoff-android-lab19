package com.pavel.tinkoffnews.local;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.pavel.tinkoffnews.local.dao.NewsDao;
import com.pavel.tinkoffnews.model.Content;
import com.pavel.tinkoffnews.model.Title;


/**
 * Created by p.mazhnik on 16.05.2019.
 * to tinkoff-android-lab19
 */

@Database(entities = {Title.class, Content.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    private static final String DB_NAME = "tinkoff_news.db";

    public abstract NewsDao mNewsDao();

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
