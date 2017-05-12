package com.aminesghir.leaguehelper.Data.Database;

import android.provider.BaseColumns;

/**
 * Created by user on 11/05/2017.
 */

public final class DatabaseContract {


    public static final String DATABASE_NAME = "league_helper";
    public static final int DATABASE_VERSION = 1;

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMM_SPA = ",";
    public static final String INTEGER_TYPE = " INTEGER";

    /** Description de la table des Posts **/
    public static final class ResearchTable implements BaseColumns {

        public static final String TABLE_NAME = "research_table";

        public static final String ID_COLUMN = "id";
        public static final String KEYWORD_COLUMN = "keyword";
        public static final String DATE_COLUMN  ="date";



        public static final String SQL_CREATE_RESEARCH_TABLE =
                //----------------------------------------------
                "CREATE TABLE IF NOT EXISTS  " +  TABLE_NAME +" ("+
                        ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        KEYWORD_COLUMN + " TEXT,"+
                        DATE_COLUMN +" TEXT );";
        //-------------------------------------------------
        public static final String SQL_DROP_RESEARCH_TABLE =  "DROP TABLE IF EXISTS "+TABLE_NAME+";";

        public static String[] PROJECTIONS = new String[] {
                KEYWORD_COLUMN,
                DATE_COLUMN
        };
    }

}
