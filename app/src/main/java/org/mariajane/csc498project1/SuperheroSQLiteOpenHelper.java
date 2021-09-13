package org.mariajane.csc498project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SuperheroSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "superheroes";
    private static final int DB_VERSION = 1;

    public SuperheroSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SUPERHERO (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "PICTURE INTEGER);");
        insertHero(db, "Black Panther", R.drawable.blackpanther);
        insertHero(db, "Black Widow", R.drawable.blackwidow);
        insertHero(db, "Captain America", R.drawable.captainamerica);
        insertHero(db, "Doctor Strange", R.drawable.doctorstrange);
        insertHero(db, "Gamora", R.drawable.gamora);
        insertHero(db, "Iron Man", R.drawable.ironman);
        insertHero(db, "Thor", R.drawable.thor);
        insertHero(db, "Vision", R.drawable.vision);
        insertHero(db, "Wanda", R.drawable.wanda);
        insertHero(db, "Winter Soldier", R.drawable.wintersoldier);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertHero(SQLiteDatabase db, String name, int picture)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("PICTURE", picture);
        db.insert("SUPERHERO", null, contentValues);
    }
}
