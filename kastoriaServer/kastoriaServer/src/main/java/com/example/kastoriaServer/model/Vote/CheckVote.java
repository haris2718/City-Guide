package com.example.kastoriaServer.model.Vote;

import com.example.kastoriaServer.model.place.Place;
import com.example.kastoriaServer.model.place.PlaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckVote {
    boolean hasVote=false;
    Vote previousVote;
    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private VoteDao voteDao;

    public Vote Checkvote(Place place,String email,Float rating){

        List<Vote> curentVoteList=place.getVotes();
        for (Vote vote:curentVoteList) {
            if (email.equals(vote.getEmail())){
                hasVote=true;
                previousVote=vote;
                break;

            }
        }
        if (hasVote==false){
            Vote vote=new Vote();
            vote.setPlace(place);
            vote.setEmail(email);
            vote.setRating(rating);
            //place.setRating(rating(place));
            //placeDao.save(place);
            //placeDao.updateplace(place,rating(place));
            return vote;
        }
        if (hasVote){
            previousVote.setRating(rating);
            //place.setRating(rating(place));
            //placeDao.save(place);
            //placeDao.updateplace(place,rating(place));
            hasVote=false;
            return previousVote;
        }
        return null;
    }

    private float rating(Place place){

        float rating=0f;
        List<Vote> votelist=  place.getVotes();
        for (Vote vote:votelist) {
            rating +=vote.getRating();

        }
        if (votelist.size()!=0)
            rating=(rating/votelist.size());
        return rating;
    }

}
