package com.example.loseit_androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExerciseHelper extends SQLiteOpenHelper {
    public ExerciseHelper(Context context) {
        super(context, "myExercises", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Exercises(userID TEXT, exerciseName TEXT," +
                "duration TEXT, calories TEXT, category TEXT,description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS Exercises");
    }

    // INSERTION:
    public boolean insertNewExercise(String userID, String exerciseName, String duration, String calories,String category, String description) {
        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", userID);
        contentValues.put("exerciseName", exerciseName);
        contentValues.put("duration", duration);
        contentValues.put("calories", calories);
        contentValues.put("category", category);
        contentValues.put("description", description);
        long result = DB.insert("Exercises" , null, contentValues );
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // GET:
    public Cursor getExercises() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT * FROM Exercises", null);
    }
}
