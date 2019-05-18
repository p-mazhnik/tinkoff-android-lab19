
package com.pavel.tinkoffnews.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pavel.tinkoffnews.model.Title;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */


public class Payload {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("creationDate")
    @Expose
    private CreationDate creationDate;
    @SerializedName("lastModificationDate")
    @Expose
    private LastModificationDate lastModificationDate;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("bankInfoTypeId")
    @Expose
    private Integer bankInfoTypeId;
    @SerializedName("typeId")
    @Expose
    private String typeId;

    public Payload() {
    }

    public Payload(Title title, CreationDate creationDate, LastModificationDate lastModificationDate, String content, Integer bankInfoTypeId, String typeId) {
        super();
        this.title = title;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.content = content;
        this.bankInfoTypeId = bankInfoTypeId;
        this.typeId = typeId;
    }

    public static class CreationDate {

        @SerializedName("milliseconds")
        @Expose
        private long milliseconds;

        public CreationDate() {
        }

        public CreationDate(long milliseconds) {
            super();
            this.milliseconds = milliseconds;
        }

        public long getMilliseconds() {
            return milliseconds;
        }

        public void setMilliseconds(Integer milliseconds) {
            this.milliseconds = milliseconds;
        }

    }

    public static class LastModificationDate {

        @SerializedName("milliseconds")
        @Expose
        private long milliseconds;

        public LastModificationDate() {
        }

        public LastModificationDate(long milliseconds) {
            super();
            this.milliseconds = milliseconds;
        }

        public long getMilliseconds() {
            return milliseconds;
        }

        public void setMilliseconds(Integer milliseconds) {
            this.milliseconds = milliseconds;
        }

    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(CreationDate creationDate) {
        this.creationDate = creationDate;
    }

    public LastModificationDate getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LastModificationDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(Integer bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}
