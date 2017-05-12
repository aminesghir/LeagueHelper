package com.aminesghir.leaguehelper.Data.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aminesghir.leaguehelper.Data.Model.Research;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/05/2017.
 */

public class ResearchDao {
    private final DatabaseHelper DbHelper;

    public ResearchDao(DatabaseHelper DbHelper) {
        this.DbHelper = DbHelper;
    }

    public boolean isDataAlreadyInDBorNot(String TableName, String dbfield, String fieldValue) {
        SQLiteDatabase sqldb = DbHelper.getReadableDatabase();
        String Query = "SELECT * FROM '"+TableName+"' where '"+dbfield+"' = '"+fieldValue+"'";
            Cursor cursor = sqldb.rawQuery(Query, null);
            if(cursor.getCount() <= 0){
                cursor.close();
                return false;
            }
            cursor.close();
            return true;

    }

    public void save(String text){
        Research research = new Research(text);
        if(isDataAlreadyInDBorNot(DatabaseContract.ResearchTable.TABLE_NAME, DatabaseContract.ResearchTable.KEYWORD_COLUMN, text)){
            DbHelper.getWritableDatabase()
                    .update("'"+DatabaseContract.ResearchTable.TABLE_NAME+"'",
                            research.toContentValues(),
                            "'"+DatabaseContract.ResearchTable.KEYWORD_COLUMN +"' = '"+text+"'",
                            null);
        }else {
            DbHelper.getWritableDatabase().insert("'"+DatabaseContract.ResearchTable.TABLE_NAME+"'", null, research.toContentValues());
        }
    }
/*
    public long save(Research research) {
        //-------------------------------
        DbHelper.getWritableDatabase().delete(DatabaseContract.ResearchTable.TABLE_NAME, DatabaseContract.ResearchTable.RESEARCH_COLUMN+" ="+String.valueOf(research.getText()), null);
        //-------------------------------
        return DbHelper.getWritableDatabase()
                .insert(DatabaseContract.ResearchTable.TABLE_NAME, null, research.toContentValues());
    }

    public List<Research> retrievePosts() {


        Cursor cursor = productHuntDbHelper.getReadableDatabase()
                .query(DataBaseContract.PostTable.TABLE_NAME,
                        DataBaseContract.PostTable.PROJECTIONS,
                        null, null, null, null, null);

        List<Research> posts = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            do {

                Post post = new Post();

                post.setId(cursor.getInt(0));
                post.setTitle(cursor.getString(1));
                post.setSubTitle(cursor.getString(2));
                post.setUrlImage(cursor.getString(3));
                post.setPostUrl(cursor.getString(4));
                //---------------------------------------------------
                post.setDate(cursor.getString(5));
                post.setNbComments(cursor.getInt(6));
                //---------------------------------------------------
                posts.add(post);


            } while (cursor.moveToNext());
        }

        return posts;
    }
    */
}
