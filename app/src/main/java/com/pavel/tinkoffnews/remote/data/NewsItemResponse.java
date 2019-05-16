
package com.pavel.tinkoffnews.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */

public class NewsItemResponse {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("payload")
    @Expose
    private Payload payload;
    @SerializedName("trackingId")
    @Expose
    private String trackingId;

    public NewsItemResponse() {
    }

    public NewsItemResponse(String resultCode, Payload payload, String trackingId) {
        super();
        this.resultCode = resultCode;
        this.payload = payload;
        this.trackingId = trackingId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

}
