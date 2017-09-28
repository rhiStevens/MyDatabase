package com.example.ishpa.mydatabase;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    EditText etMovieName ;
    TextView tvDisplay ;
    MyDBHandler dbHandler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovieName = (EditText)findViewById(R.id.etMovieName) ;
        tvDisplay = (TextView)findViewById(R.id.tvDisplay);
        // we pass 4 variables and the constuctor is expecting 4
        dbHandler = new MyDBHandler(this, null,null,1) ;
        printDatabase() ;

    }

    public void printDatabase() {

        try {

            //convert contents of database to string
            String dbString = dbHandler.databaseToString();

            tvDisplay.setText(dbString);

        }
      catch (Exception e) {

          Log.e("Error", e.toString());
      }

    }
    // overloading  - when a user is deleted from the database it is replaces with an empty string
    public void printDatabase(String display) {

        try {
            // convert contents of database to string
            String dbString = dbHandler.databaseToString();
            tvDisplay.setText(dbString);
        }
        catch (Exception e){
            Log.e("Error", e.toString());
        }
    }

    public void addMovie(View view) {

        try {
            //create object of movies class passing the movie name we input and converting to string
            Movies movies = new Movies(etMovieName.getText().toString());
            dbHandler.addMovies(movies);
            //print content everytime new movie is added
            printDatabase();


        } catch (Exception e){
            Log.e("Error: Add failed", e.toString());
        }
    }

    public void deleteMovie(View view){
        // string to store input from user in teh edittext
        String inputMovie = etMovieName.getText().toString();
        // pass string to dbHandler deleteMovies() method
        dbHandler.deleteMovies(inputMovie);
        //print database once deleted
        printDatabase();
    }

    public void editMovie(View view) {
        String inputMovie = etMovieName.getText().toString();
        dbHandler.editMovies(inputMovie);
        printDatabase();
    }

}
