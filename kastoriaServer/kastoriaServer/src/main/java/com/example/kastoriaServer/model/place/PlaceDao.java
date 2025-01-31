package com.example.kastoriaServer.model.place;

import com.example.kastoriaServer.model.Vote.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlaceDao {
    @Autowired
    private PlaceRepository repository;
    public Place save(Place place) {
        return repository.save(place);
    }
    /*
    @Autowired
    public void  updateplace(Place place,float rating) {
        //https://stackoverflow.com/questions/60258574/javas-optional-forces-itself-or-else-i-get-an-error
        Place placefromDb = repository.findById(place.getId()).get();
        // crush the variables of the object found
        placefromDb.setRating(rating);
        repository.save(placefromDb);
    }
*/
//    public void insertPlaces(List<Place> Places) {
//        repository.saveAll(Places);
//    }

    public List<Place> insertPlaces(List<Place> Places) {
        List<Place> savedPlaces = new ArrayList<Place>();
        for (Place place : Places) {
            savedPlaces.add(repository.save(place));
        }

        return(List<Place>)savedPlaces;

    }


    public List<Place> getAllPlaces() {
        List<Place> Places = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(Places::add);
        //υπολογισε τα rating κάθε μέρους
       /*Streamable.of(Places)
                .forEach((place -> place.setRating(calcRating(place.getVotes()))));*/
        /*for (Place place:Places) {
            place.setRating(calcRating(place.getVotes()));
            repository.;
        }*/

        return Places;
    }

    public void delete(int PlaceId) {
        repository.deleteById(PlaceId);
    }

    public void deleteAll(){repository.deleteAll();}


    public Place findPlaceByid (int id){
        //το optional-forces-itself γιαυτο βάζουμε το get()
        //Place p=repository.findById(id).get();
        //υπολόγισε το rating του μέρους
        //p.calcRating();
        //return p;
        return repository.findById(id).get();

    }



}
