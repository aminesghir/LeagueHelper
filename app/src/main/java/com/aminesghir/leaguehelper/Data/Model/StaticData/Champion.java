package com.aminesghir.leaguehelper.Data.Model.StaticData;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Amine on 09/05/2017.
 */

public class Champion {

    private long id;
    private String key;
    private String name;
    private String title;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

