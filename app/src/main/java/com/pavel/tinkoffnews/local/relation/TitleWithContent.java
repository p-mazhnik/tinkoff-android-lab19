package com.pavel.tinkoffnews.local.relation;

import androidx.room.Embedded;

import com.pavel.tinkoffnews.model.Content;
import com.pavel.tinkoffnews.model.Title;

/**
 * Created by p.mazhnik on 18.05.2019.
 * to tinkoff-android-lab19
 */
public class TitleWithContent {
    @Embedded
    private Title title;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Embedded
    private Content content;
}
