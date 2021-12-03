package int222.backend.models.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import int222.backend.models.entities.Movie;
import int222.backend.repositories.MovieRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("movieService")
@Scope("singleton")
@NoArgsConstructor
public class MovieService {
    @Autowired
    private MovieRepository movieRepo;
    public Movie convertJsonStringToMovie(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Movie newMovie = mapper.readValue(jsonString, Movie.class);
        return newMovie;
    }

    public Boolean checkNameIsAlreadyExists(String movieName){
        return movieRepo.existsByName(movieName);
    }




}
