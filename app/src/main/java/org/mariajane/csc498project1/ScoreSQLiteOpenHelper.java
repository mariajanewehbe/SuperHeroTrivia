package org.mariajane.csc498project1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ScoreSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "scores";
    private static final int DB_VERSION = 1;

    public ScoreSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORES (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "SCORE INTEGER,"+
                "AVERAGE REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
