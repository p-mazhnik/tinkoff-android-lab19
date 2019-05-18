package com.pavel.tinkoffnews.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by p.mazhnik on 18.05.2019.
 * to tinkoff-android-lab19
 */

@Entity(tableName = Content.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Title.class,
            parentColumns = Title.COLUMN_ID,
            childColumns = Content.COLUMN_TITLE_ID,
            onDelete = ForeignKey.NO_ACTION),
        indices = {@Index(Content.COLUMN_TITLE_ID)})
public class Content {
    @ColumnInfo(name = Content.COLUMN_TITLE_ID)
    @PrimaryKey
    @NonNull
    private String title_id;

    @SerializedName("content")
    @Expose
    @ColumnInfo(name = Content.COLUMN_CONTENT)
    private String content;

    public Content(){}

    @Ignore
    public Content(@NonNull String title_id, String content) {
        this.title_id = title_id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NonNull
    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(@NonNull String title_id) {
        this.title_id = title_id;
    }

    @Ignore
    public static final String TABLE_NAME = "news_content";
    @Ignore
    public static final String COLUMN_CONTENT = "content";
    @Ignore
    public static final String COLUMN_ID = "content_id";
    @Ignore
    public static final String COLUMN_TITLE_ID = "title_id";
}
