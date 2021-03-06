package com.aminesghir.leaguehelper.Data.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 11/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_LIVRES = "table_livres";
    private static final String COL_ID = "ID";
    private static final String COL_ISBN = "ISBN";
    private static final String COL_TITRE = "Titre";


    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.ResearchTable.SQL_CREATE_RESEARCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.ResearchTable.SQL_DROP_RESEARCH_TABLE);
        onCreate(db);
    }
}
