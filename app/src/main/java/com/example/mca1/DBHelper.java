package com.example.mca1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "kasturi.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataModel.AppTable.TABLE_NAME + " (" +
                    DataModel.AppTable._ID + " INTEGER PRIMARY KEY," +
                    DataModel.AppTable.COLUMN_1 + " DOUBLE DEFAULT 0," +
                    DataModel.AppTable.COLUMN_2 + " DOUBLE DEFAULT 0," +
                    DataModel.AppTable.COLUMN_3 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_4 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_5 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_6 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_7 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_8 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_9 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_10 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_11 + " INTEGER DEFAULT 0," +
                    DataModel.AppTable.COLUMN_12 + " INTEGER DEFAULT 0)" ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataModel.AppTable.TABLE_NAME;
        public DBHelper(Context context) {
            super(context,  "/sdcard/Android/data/com.example.mca1/files" + File.separator  + DATABASE_NAME , null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

