package com.example.testapi.pokeapi;

import com.google.gson.annotations.SerializedName;

public class MoveInner {

    @SerializedName("accuracy")
    private String accuracy;

    @SerializedName("power")
    private String power;

    @SerializedName("pp")
    private String pp;

    public String getAccuracy() {
        return accuracy;
    }

    public String getPower() {
        return power;
    }

    public String getPp() {
        return pp;
    }

    @SerializedName("type")
    private Type type;

    public Type getType() {
        return type;
    }

    public static class Type{
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }
}
