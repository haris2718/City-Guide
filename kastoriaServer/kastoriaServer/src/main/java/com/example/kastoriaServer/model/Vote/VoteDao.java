package com.example.kastoriaServer.model.Vote;

import com.example.kastoriaServer.model.place.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteDao {
    @Autowired
    private VoteRepository repository;
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    public List<Vote> getAllVotes() {
        List<Vote> Votes = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(Votes::add);
        return Votes;
    }

    public void delete(Long VoteId) {
        repository.deleteById(VoteId);
    }

    public void deleteAll(){
        repository.deleteAll();
    }


}

