
package com.pavel.tinkoffnews.remote.data;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pavel.tinkoffnews.model.Title;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */

public class NewsListResponse {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("payload")
    @Expose
    private List<Title> mTitle = new ArrayList<Title>();

    public NewsListResponse() {
    }

    public NewsListResponse(String resultCode, List<Title> title) {
        super();
        this.resultCode = resultCode;
        this.mTitle = title;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<Title> getTitle() {
        return mTitle;
    }

    public void setTitle(List<Title> title) {
        this.mTitle = title;
    }

}
