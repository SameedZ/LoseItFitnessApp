package com.example.loseit_androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "myMeals.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Meals(userID TEXT primary key, type TEXT, calories TEXT, description TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS MEALS");
    }

    // INSERTION:
    public boolean insertNewMeal(String userID, String type, String calories, String description) {
        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", userID);
        contentValues.put("type", type);
        contentValues.put("calories", calories);
        contentValues.put("description", description);
        long result = DB.insert("Meals" , null, contentValues );
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // UPDATE:
    public boolean updateMeal(String userID, String type, String calories, String description) {
        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("calories", calories);
        contentValues.put("description", description);
        try (Cursor cursor = DB.rawQuery("SELECT * FROM Meals WHERE userID=?", new String[]{userID})) {

            if (cursor.getCount() > 0) {
                long result = DB.update("Meals", contentValues, "userID?", new String[]{userID});
                return result != -1;
            } else {
                return false;
            }
        }

    }


    // DELETE:
    public boolean deleteMeal(String userID) {
        SQLiteDatabase DB = this.getReadableDatabase();
        try (Cursor cursor = DB.rawQuery("SELECT * FROM Meals WHERE userID=?", new String[]{userID})) {

            if (cursor.getCount() > 0) {
                long result = DB.delete("Meals", "userID?", new String[]{userID});
                return result != -1;
            } else {
                return false;
            }
        }

    }

    // GET:
    public Cursor getMeal() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT * FROM Meals", null);


    }

}
