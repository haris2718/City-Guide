package com.example.kastoria_guide.Model;

public class Vote {
    private Long id;
    private String email;
    private Float rating;
    private int placeid;

    public Vote( String email, Float rating, int placeid) {
        //this.id = id;
        this.email = email;
        this.rating = rating;
        this.placeid = placeid;
    }
/*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public int getPlaceid() {
        return placeid;
    }

    public void setPlaceid(int placeid) {
        this.placeid = placeid;
    }
}
