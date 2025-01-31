package com.example.kastoriaServer.Controller;

import com.example.kastoriaServer.model.Vote.CheckVote;
import com.example.kastoriaServer.model.Vote.Vote;
import com.example.kastoriaServer.model.Vote.VoteDao;
import com.example.kastoriaServer.model.place.Place;
import com.example.kastoriaServer.model.place.PlaceDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VoteController {
    @Autowired
    private PlaceDao placeDao;
    @Autowired
    private VoteDao voteDao;

    @Autowired
    private CheckVote checkVote;

    //το endpoint που θα χρησιμοποίηση το rest api για να
    // πάρει όλους τους ψήφους
    @GetMapping("/vote/get-all")
    public List<Vote> getAllVotes() {
        return voteDao.getAllVotes() ;
    }
    @GetMapping("/places/{placeid}/Votes")
    public List<Vote> getThisPlaceVotes(@PathVariable int placeid)  {
        Place place=placeDao.findPlaceByid(placeid);
        return place.getVotes();
    }

    @PostMapping("/places/{placeId}/Vote")
    public Vote saveVote(@PathVariable int placeId, @RequestBody Vote vote)  {
        Place place = placeDao.findPlaceByid(placeId);
        Vote checkedvote=checkVote.Checkvote(place,vote.getEmail(),vote.getRating());
        return voteDao.save(checkedvote);
    }
}

