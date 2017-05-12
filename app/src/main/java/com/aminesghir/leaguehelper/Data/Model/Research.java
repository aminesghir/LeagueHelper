package com.aminesghir.leaguehelper.Data.Model;

import android.content.ContentValues;

import com.aminesghir.leaguehelper.Data.Database.DatabaseContract;

import java.util.Date;

/**
 * Created by user on 11/05/2017.
 */

public class Research {
    private String text;
    private String date;

    public Research(String s){
        this.text = s;
        this.date = String.valueOf(new Date());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ResearchTable.KEYWORD_COLUMN, text);
        contentValues.put(DatabaseContract.ResearchTable.DATE_COLUMN, date);

        return contentValues;
    }
}
