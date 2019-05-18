
package com.pavel.tinkoffnews.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */

@Entity(tableName = Title.TABLE_NAME)
public class Title {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Title.COLUMN_ID)
    private String id;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = Title.COLUMN_NAME)
    private String name;
    @SerializedName("text")
    @Expose
    @ColumnInfo(name = Title.COLUMN_TEXT)
    private String text;
    @SerializedName("publicationDate")
    @Expose
    @Embedded
    private PublicationDate publicationDate;

    @SerializedName("bankInfoTypeId")
    @Expose
    private long bankInfoTypeId;

    public Title(@NonNull String id, String name, String text, PublicationDate publicationDate, long bankInfoTypeId) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.publicationDate = publicationDate;
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public static class PublicationDate {

        @SerializedName("milliseconds")
        @Expose
        @ColumnInfo(name = Title.COLUMN_PUB_DATE)
        private long milliseconds;

        public PublicationDate(long milliseconds) {
            this.milliseconds = milliseconds;
        }

        public long getMilliseconds() {
            return milliseconds;
        }

        public void setMilliseconds(Integer milliseconds) {
            this.milliseconds = milliseconds;
        }

    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(PublicationDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public long getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(long bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    @Ignore
    public static final String TABLE_NAME = "news";
    @Ignore
    public static final String COLUMN_ID = "id";
    @Ignore
    public static final String COLUMN_TEXT = "text";
    @Ignore
    public static final String COLUMN_NAME = "name";
    @Ignore
    public static final String COLUMN_PUB_DATE = "pub_date";

}
