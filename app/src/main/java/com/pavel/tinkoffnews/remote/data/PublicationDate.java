
package com.pavel.tinkoffnews.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */

public class PublicationDate {

    @SerializedName("milliseconds")
    @Expose
    private long milliseconds;

    public PublicationDate() {
    }

    public PublicationDate(long milliseconds) {
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
