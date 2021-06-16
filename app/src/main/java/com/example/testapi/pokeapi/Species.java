package com.example.testapi.pokeapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Species {
    @SerializedName("flavor_text_entries")
    public ArrayList<Flavor_text_entries> flavor_text_entries;

    @SerializedName("gender_rate")
    private String gender_rate;
    public String getGender_rate() {
        return gender_rate;
    }

    @SerializedName("habitat")
    public Habitat habitat;


    public ArrayList<Flavor_text_entries> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public static class Flavor_text_entries{
        @SerializedName("flavor_text")
        public String flavor_text;

        @SerializedName("language")
        public Language language;

        public String getFlavor_text() {
            return flavor_text;
        }

        public static class Language{
            @SerializedName("name")
            private String name;

            @SerializedName("url")
            private String url;

            public String getUrl() {
                return url;
            }

            public String getName() {
                return name;
            }
        }

        public Language getLanguage() {
            return language;
        }
    }

    public static class Habitat{
        @SerializedName("name")
        public String name;

        public String getName() {
            return name;
        }
    }

    public Habitat getHabitat(){
        return habitat;
    }

    


}



