package com.aminesghir.leaguehelper.Data.Database;

import android.provider.BaseColumns;

/**
 * Created by user on 11/05/2017.
 */

public final class DatabaseContract {


    public static final String DATABASE_NAME = "LeagueHelper";
    public static final int DATABASE_VERSION = 1;

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMM_SPA = ",";
    public static final String INTEGER_TYPE = " INTEGER";

    /** Description de la table des Posts **/
    public static final class ResearchTable implements BaseColumns {

        public static final String TABLE_NAME = "research";

        public static final String RESEARCH_COLUMN = "research";
        public static final String DATE_COLUMN  ="date";



        public static final String SQL_CREATE_POST_TABLE =
                //----------------------------------------------
                "CREATE TABLE" +ResearchTable.TABLE_NAME+" ("+
                        RESEARCH_COLUMN + TEXT_TYPE +" PRIMARY KEY"+COMM_SPA+
                        DATE_COLUMN + TEXT_TYPE + ");";
        //-------------------------------------------------
        public static final String SQL_DROP_POST_TABLE =  "DROP TABLE IF EXISTS "+TABLE_NAME;

        public static String[] PROJECTIONS = new String[] {
                RESEARCH_COLUMN,
                DATE_COLUMN,
        };
    }

}
