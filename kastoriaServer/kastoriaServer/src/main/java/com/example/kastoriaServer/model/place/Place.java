package com.example.kastoriaServer.model.place;

import com.example.kastoriaServer.model.Vote.Vote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name="places")
public class Place {
    //ΓΙΑ ΝΑ ΑΝΑΓΝΩΡΙΣΕΙ ΤΟ PRIMARY KEY
    @Id
    //ΓΙΑ ΝΑ  AYΞANETAI MONO TOY TO ΚΛΕΙΔΙ ΚΑΤΑ ΕΝΑ
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "rating")
    private float rating;
    @Column(name = "lat")
    private float lat;
    @Column(name = "lon")
    private float lon;
    @Column(name = "category")
    private String category;
    @Column(name = "distance")
    private float distance;
    @Column(name = "description")
    private String description;
    @Column(name = "placeUrl")
    private String placeUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "place",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }
    @JsonIgnore
    public List<Vote> getVotes() {

        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating=" + rating +
                ", lat=" + lat +
                ", lon=" + lon +
                ", category='" + category + '\'' +
                ", distance=" + distance +
                ", description='" + description + '\'' +
                ", placeUrl='" + placeUrl + '\'' +
                '}';
    }
}
