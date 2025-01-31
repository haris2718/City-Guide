package com.example.kastoria_guide.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import androidx.room.Entity;

import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "Place_table")
public class Place implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "imageurl")
    private String imageUrl;
    @NonNull
    @ColumnInfo(name = "rating")
    private float rating;
    @NonNull
    @ColumnInfo(name = "lat")
    private float lat;
    @NonNull
    @ColumnInfo(name = "lon")
    private float lon;
    @NonNull
    @ColumnInfo(name = "category")
    private String category;
    @NonNull
    @ColumnInfo(name = "distance")
    private float distance;
    @NonNull
    @ColumnInfo(name = "description")
    private String description;
    @NonNull
    @ColumnInfo(name = "placeUrl")
    private String placeUrl;
/*
  @Ignore
    public Place (){};
 */


    public Place(@NonNull String name, @NonNull String imageUrl, float rating, float lat, float lon,
                 @NonNull String category, float distance, @NonNull String description, @NonNull String placeUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.lat = lat;
        this.lon = lon;
        this.category = category;
        this.distance = distance;
        this.description = description;
        this.placeUrl = placeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
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

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(@NonNull String placeUrl) {
        this.placeUrl = placeUrl;
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


    //Ο comparator επιστρέφει έναν αριθμό, εάν τα πεδία που έπιλέξουμε απο το μέρος
    // είναι ίσα επιστρέφει 0
    //εαν το πεδίο του  h1 είναι πριν του H2 επιστρέφει θετικό αλλίως αρνητικό
    public static Comparator<Place> PlaceNameAZComparetor = new Comparator<Place>() {
        @Override
        public int compare(Place p1, Place p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };
    //επιστρέφει με αντίστροφη αλφαβητική σειρά (αντιστρέφουμε τo h1 με το h2)
    public static Comparator<Place> PlaceNameZAComparetor = new Comparator<Place>() {
        @Override
        public int compare(Place p1, Place p2) {
            return p2.getName().compareTo(p1.getName());
        }
    };

    //για java 1.4 και πάνω
    public static Comparator<Place> PlaceDistanceASCComparetor = new Comparator<Place>() {
        @Override
        public int compare(Place p1, Place p2) {
            return  Float.compare(p1.getDistance(), p2.getDistance());
        }
    };

    public static Comparator<Place> PlaceDistanceDESCComparetor = new Comparator<Place>() {
        @Override
        public int compare(Place p1, Place p2) {
            return  Float.compare(p2.getDistance(), p1.getDistance());
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeFloat(this.rating);
        dest.writeFloat(this.lat);
        dest.writeFloat(this.lon);
        dest.writeString(this.category);
        dest.writeFloat(this.distance);
        dest.writeString(this.description);
        dest.writeString(this.placeUrl);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.imageUrl = source.readString();
        this.rating = source.readFloat();
        this.lat = source.readFloat();
        this.lon = source.readFloat();
        this.category = source.readString();
        this.distance = source.readFloat();
        this.description = source.readString();
        this.placeUrl = source.readString();
    }

    protected Place(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.rating = in.readFloat();
        this.lat = in.readFloat();
        this.lon = in.readFloat();
        this.category = in.readString();
        this.distance = in.readFloat();
        this.description = in.readString();
        this.placeUrl = in.readString();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
