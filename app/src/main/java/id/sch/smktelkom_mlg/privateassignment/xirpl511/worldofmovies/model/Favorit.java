package id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Smktelkom on 5/15/2017.
 */

public class Favorit extends SugarRecord implements Serializable {

    public String title;
    public String overview;
    public String poster_path;
    public String release_date;
    public String vote_count;
    public String vote_average;

    public Favorit() {

    }

    public Favorit(String title, String overview, String poster_path, String release_date,
                   String vote_count, String vote_average) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
    }
}
