package com.example.testapi.pokeapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovesGet {

    @SerializedName("moves")
    private ArrayList<Moves> moves;

    public ArrayList<Moves> getMoves() { return moves; }

    @SerializedName("types")
    private ArrayList<Types> types;

    public ArrayList<Types> getTypes() { return types; }

    public static class Types{
        @SerializedName("type")
        private Type type;

        public Type getType() { return type; }

        public static class Type{
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }
        }
    }

    public static class Moves{
        @SerializedName("move")
        private Move move;

        @SerializedName("version_group_details")
        private ArrayList<Version_group_details> version_group_details;

        public Move getMove() {
            return move;
        }



        public ArrayList<Version_group_details> getVersion_group_details() {
            return version_group_details;
        }

        public static class Move{
            @SerializedName("name")
            private String name;

            @SerializedName("url")
            private String url;

            public String getName() {
                return name;
            }

            public String getUrl() {
                return url;
            }
        }

        public static class Version_group_details{
            @SerializedName("level_learned_at")
             private String level_learned_at;

             public String getLevel_learned_at() {
             return level_learned_at;
            }
        }
    }

}

