package com.karbonara.karbonara;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Anime {
    public String poster;
    public String title;
    public int readyEpisodes;
    public int allEpisodes;
    public String description;
    public int id;
    public String titleRus;
    public String title2RUS;
    public String[] listMembers;
    public String readyStatus = "";

    public Anime(int id, String title, String titleRus, String title2RUS, int readyEpisodes, int allEpisodes, String descriptionRus, String s, String poster) {
        this.id = id;
        this.title = title;
        this.titleRus = titleRus;
        if (!title2RUS.equals("null")) this.title2RUS = title2RUS;
        else this.title2RUS = null;
        this.readyEpisodes = readyEpisodes;
        this.allEpisodes = allEpisodes;
        if (readyEpisodes != allEpisodes) this.readyStatus = "[" + readyEpisodes + "/" + allEpisodes + "]";
        this.description = descriptionRus;
        this.listMembers = s.split(", ");
        this.poster = poster;
    }
    public Anime() {
        this.id = 1;
        this.title = "title";
        this.titleRus = "titleRus";
        //if (!title2RUS.equals("null"))
        this.title2RUS = "title2RUS";
        //else this.title2RUS = null;
        this.readyEpisodes = 1;
        this.allEpisodes = 12;
        this.readyStatus = "[" + readyEpisodes + "/" + allEpisodes + "]";
        this.description = "descriptionRus";
        this.listMembers = new String[]{"s.split(", ")"};
    }

    public void updateReadyStatus() {
        this.readyStatus = "[" + readyEpisodes + "/" + allEpisodes + "]";
    }

    public String getTitle() {
        return titleRus;
    }
}
