package com.example.ishpa.mydatabase;

import java.util.Date;

/**
 * Created by ishpa on 15/05/2017.
 */

public class Movies {

    private int _id;
    private String _movieName ;

    public Movies() {

    }

    public Movies(String movieName) {
        this._movieName = movieName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_movieName(String _movieName) {
        this._movieName = _movieName;
    }

    public int get_id() {
        return _id;
    }

    public String get_movieName() {
        return _movieName;
    }
}
