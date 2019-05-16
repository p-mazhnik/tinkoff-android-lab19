package com.pavel.tinkoffnews.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.pavel.tinkoffnews.remote.data.Title;

/**
 * Created by p.mazhnik on 16.05.2019.
 * to tinkoff-android-lab19
 */

@Entity(tableName = NewsEntity.TABLE_NAME)
public class NewsEntity {
    @PrimaryKey
    @ColumnInfo(name = NewsEntity.COLUMN_ID)
    private long mId;

    @ColumnInfo(name = NewsEntity.COLUMN_TEXT)
    private String mText;

    @ColumnInfo(name = NewsEntity.COLUMN_CONTENT)
    private String mContent;

    @ColumnInfo(name = NewsEntity.COLUMN_PUB_DATE)
    private long mPubDate;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public long getPubDate() {
        return mPubDate;
    }

    public void setPubDate(long pubDate) {
        mPubDate = pubDate;
    }

    public NewsEntity(){

    }

    @Ignore
    public NewsEntity(Title title){
        setId(Long.parseLong(title.getId()));
        setText(title.getText());
        setPubDate(title.getPublicationDate().getMilliseconds());
    }

    @Ignore
    public static final String TABLE_NAME = "news";
    @Ignore
    public static final String COLUMN_ID = "id";
    @Ignore
    public static final String COLUMN_TEXT = "text";
    @Ignore
    public static final String COLUMN_CONTENT = "content";
    @Ignore
    public static final String COLUMN_PUB_DATE = "pub_date";

}
