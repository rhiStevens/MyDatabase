package com.example.ishpa.mydatabase;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void addMovieButtonClicked(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void aboutMovieButtonClicked (View v) {
        Intent intent = new Intent(this, ChangeTheme.class);
        startActivity(intent);
    }

    // onClick method for changing the background color
    public void onClick (View v) {
        ConstraintLayout color = (ConstraintLayout) findViewById(R.id.layout_home);
        color.setBackgroundColor(Color.BLACK);
    }
}
