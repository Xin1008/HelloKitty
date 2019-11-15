package com.example.hellokitty;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cat implements Serializable {

    private String id;
    private String name;
    private String description;
    private String temperament;
    private String origin;
    private String life_span;
    private String wikipedia_url;
    private int dogFriendlinessLevel;
    @SerializedName("weight")
    private Weight weight;

    public Cat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public int getDogFriendlinessLevel() {
        return dogFriendlinessLevel;
    }

    public void setDogFriendlinessLevel(int dogFriendlinessLevel) {
        this.dogFriendlinessLevel = dogFriendlinessLevel;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public class Weight implements Serializable {
        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }
}
