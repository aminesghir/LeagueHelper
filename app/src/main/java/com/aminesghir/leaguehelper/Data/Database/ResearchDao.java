package com.aminesghir.leaguehelper.Data.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.Model.Research;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/05/2017.
 */

public class ResearchDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ResearchDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public long insertResearch(Research research) {
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.ResearchTable.KEYWORD_COLUMN, research.getText());
        values.put(DatabaseContract.ResearchTable.DATE_COLUMN, research.getDate());

        return db.insert(DatabaseContract.ResearchTable.TABLE_NAME, null, values);
    }

    public long updateResearch(String text, Research research) {
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.ResearchTable.KEYWORD_COLUMN, research.getText());
        values.put(DatabaseContract.ResearchTable.DATE_COLUMN, research.getDate());
        String[] args = new String[]{text};
        return db.update(DatabaseContract.ResearchTable.TABLE_NAME, values, DatabaseContract.ResearchTable.KEYWORD_COLUMN + " =?", args);
    }

    public int deleteResearch(String text){
        String[] args = new String[]{text};
        return db.delete(DatabaseContract.ResearchTable.TABLE_NAME, DatabaseContract.ResearchTable.KEYWORD_COLUMN + " =? ", args);
    }

    public List<Research> cursorToListResearch(Cursor c){
        if(c.getCount() == 0){
            return null;
        }

        List<Research> list = new ArrayList<>(c.getCount());
        c.moveToFirst();
        while(!c.isAfterLast() ){
            Research research = new Research();

            research.setText(c.getString(0));
            research.setDate(c.getString(1));

            list.add(research);
            c.moveToNext();
        }

        c.close();

        return list;
    }

    public Research getResearch(String text){
        Cursor c = db.query(DatabaseContract.ResearchTable.TABLE_NAME, new String[]{DatabaseContract.ResearchTable.KEYWORD_COLUMN, DatabaseContract.ResearchTable.DATE_COLUMN}, DatabaseContract.ResearchTable.KEYWORD_COLUMN + " LIKE \"" + text +"\"", null, null, null, DatabaseContract.ResearchTable.DATE_COLUMN + " DESC", String.valueOf(1));
        if(c.getCount() > 0) {
            return cursorToListResearch(c).get(0);
        }else{
            return null;
        }
    }

    public List<Research> getRecentResearches(int size){
        Cursor c = db.query(DatabaseContract.ResearchTable.TABLE_NAME, new String[]{DatabaseContract.ResearchTable.KEYWORD_COLUMN, DatabaseContract.ResearchTable.DATE_COLUMN},null, null, DatabaseContract.ResearchTable.KEYWORD_COLUMN, null, DatabaseContract.ResearchTable.DATE_COLUMN + " DESC", String.valueOf(size));
        //Cursor c = db.rawQuery("SELECT " + DatabaseContract.ResearchTable.KEYWORD_COLUMN + " FROM " + DatabaseContract.ResearchTable.TABLE_NAME, null);
        return cursorToListResearch(c);
    }

}
