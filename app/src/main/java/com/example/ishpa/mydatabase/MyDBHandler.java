package com.example.ishpa.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ishpa on 15/05/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    //database version to identify the database uniquely. So everytime you make changes to the database, you increment the version
    private static final int DATABASE_VERSION = 1  ;

    // database name
    private static final String DATABASE_NAME = "movies.db"  ;

    //table name
    private static final String TABLE_MOVIES = "movies"  ;

    //attributes in the table
    private static final String COLUMN_ID = "_id" ;
    private static final String COLUMN_MOVIENAME = "moviename" ;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_MOVIES + " ( " + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MOVIENAME + " TEXT " + ");" ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete existing table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        //call onCreate() method to create new table
       onCreate(db);

    }

    public void addMovies(Movies movies){
        try {
            // associate each value with content values
            ContentValues values = new ContentValues();
            //method to combine everything so as to insert in the table
            values.put(COLUMN_MOVIENAME, movies.get_movieName());
            //reference to the database to write something
            SQLiteDatabase db = getWritableDatabase();
            //insert the values
            db.insert(TABLE_MOVIES, null, values);
            //close the database
            db.close();
        }
        catch (Exception e) {
            Log.e("Error", e.toString());
        }

    }

    public void editMovies (String moviename ){

        SQLiteDatabase db =getWritableDatabase();

        db.execSQL("UPDATE " + TABLE_MOVIES + " SET " + COLUMN_MOVIENAME + "=\"" + moviename + "\";" );
    }

    public void deleteMovies(String moviename){

        SQLiteDatabase db =getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_MOVIES + " WHERE " + COLUMN_MOVIENAME + "=\"" + moviename + "\";");

    }

    public String databaseToString() {

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        // query to display results
        String query = "SELECT * FROM " + TABLE_MOVIES + " WHERE 1 ";
        //create cursor to point to current row
        Cursor cursor = db.rawQuery(query, null) ;
        cursor.moveToFirst();

        //while loop to loop through the table
        while(!cursor.isAfterLast()) {

            if(cursor.getString(cursor.getColumnIndex("moviename")) != null) {

                dbString+= cursor.getString(cursor.getColumnIndex("moviename"));
                //new line
                dbString+="\n";

            }
            cursor.moveToNext() ;

        }
        //close database
        db.close() ;
        return dbString;
    }
}
