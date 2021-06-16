package com.example.testapi.pokeapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post {


    private String name;
    private String id;
    private String height;
    private String weight;

    @SerializedName("stats")
    private ArrayList<Stats> stats;

    public ArrayList<Stats> getStats() {
        return stats;
    }


    public static class Stats{
        @SerializedName("base_stat")
        private String base_stat;

        public String getBase_stat() {
            return base_stat;
        }
    }

    public String getName() {
        return name;
    }

    public String getId() { return id; }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }
}

