package com.example.kastoriaServer.Controller;

import com.example.kastoriaServer.model.Vote.Vote;
import com.example.kastoriaServer.model.place.Place;
import com.example.kastoriaServer.model.place.PlaceDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PlaceController {
    @Autowired
    private PlaceDao placeDao;

    //το endpoint που θα χρησιμοποίηση το rest api για να
    // πάρει όλους τους χρήστες είναι το /employee/get-all
    @JsonIgnore
    @GetMapping("/place/get-all")
    public List<Place> getAllPlaces() {
        for (Place place : placeDao.getAllPlaces()) {
            place.setRating(calcRating(place.getVotes()));
            placeDao.save(place);

        }
        return placeDao.getAllPlaces();
    }

    @JsonIgnore
    @GetMapping("/place/{placeid}")
    public Place getPlace(@PathVariable int placeid) {
        Place place=placeDao.findPlaceByid(placeid);
        place.setRating(calcRating(place.getVotes()));
        placeDao.save(place);
        return placeDao.findPlaceByid(placeid) ;
    }

    @PostMapping("/place/save")
    public Place save(@RequestBody Place place) {
        return placeDao.save(place);
    }


    @PostMapping("/places/saveAll")
    public List<Place> saveAll(@RequestBody List<Place> places) {

        return placeDao.insertPlaces(places);
    }

    //υπολόγισε το rating του μέρους
    public float calcRating(List<Vote>votes){
        float sum=0;
        float rating=0;
        if (votes.size()!=0){
            for (Vote vote:votes) {
                sum+= vote.getRating();
            }
            return rating=sum/votes.size();
        }else{
            return    rating=0;
        }

    }


}
