package int222.backend.controllers;

import int222.backend.models.entities.Movie;
import int222.backend.models.entities.User;
import int222.backend.models.exceptions.EntityAlreadyExistsException;
import int222.backend.models.exceptions.MovieNotFoundException;
import int222.backend.models.services.ImageService;
import int222.backend.models.services.MovieService;
import int222.backend.models.services.UserService;
import int222.backend.repositories.MovieRepository;
import int222.backend.repositories.UserRepository;
import int222.backend.utilities.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.util.List;

@CrossOrigin("*")
@RestController
//@RequestMapping("/")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private MovieService movieService;


    @GetMapping("/movies")
    public List<Movie> getMovieList() {
        return this.movieRepository.findAll();
    }

    @GetMapping("/movie/{id}")
    public Movie getMovieById(@PathVariable("id") int movieId) {
        Movie respone = movieRepository.findById(movieId).orElse(null);
        if (respone == null) {
            throw new MovieNotFoundException();
        }
        return respone;
    }


    @GetMapping("/movie/name/{name}")
    public Movie getMovieByName(@PathVariable("name") String movieName) {
        Movie respone = movieRepository.findByMovienameContainsIgnoreCase(movieName).orElse(null);
        if (respone == null) {
            throw new MovieNotFoundException();
        }
        return respone;
    }

    @GetMapping("/view/status/{status_id}")
    public List<Movie> getMovieByStatus(@PathVariable("status_id") int status_id) {
        return movieRepository.findAllByStatus(status_id);
    }

    @GetMapping("/view/studio/{studio_id}")
    public List<Movie> getMovieByStudio(@PathVariable("studio_id") int studio_id) {
        return movieRepository.findAllByStudio(studio_id);
    }

    @GetMapping("/view/genre/{genre_id}")
    public List<Movie> getMovieByGenre(@PathVariable("genre_id") int genre_id) {
        return movieRepository.findByMovieGenre(genre_id);
    }

    @GetMapping("/view/genre/name/{genreName}")
    public List<Movie> getMovieByGenrename(@PathVariable("genreName") String genreName) {
        return movieRepository.findByMovieGenreNameContainsIgnoreCase(genreName);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "admin/movie/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void newMovie(@RequestParam String movie, @RequestPart MultipartFile file) throws IOException {
        if (movieService.checkNameIsAlreadyExists(movie)) {
            throw new EntityAlreadyExistsException("Movie is already exists");
        }
        Movie newMovie = movieService.convertJsonStringToMovie(movie);
        imageService.saveImg(file);
        movieRepository.save(newMovie);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("admin/movie/delete/{id}")
    private String deleteMovie(@PathVariable("id") int id) {
        if (movieRepository.findById(id).isEmpty()) {
            throw new MovieNotFoundException();
        }
        movieRepository.deleteById(id);
        return "Movie successfully deleted";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("admin/movie/edit/{id}")
    public void updateMovie(@PathVariable("id") int movie_id, @RequestParam String movie, @RequestPart(required = false) MultipartFile file) throws IOException {
        Movie newMovie = movieService.convertJsonStringToMovie(movie);
        Movie oldMovie = movieRepository.findById(movie_id).orElse(null);
        if (oldMovie.equals(null)) {
            throw new MovieNotFoundException();
        }
        if (oldMovie.getMoviename().equalsIgnoreCase(newMovie.getMoviename())) {
            throw new EntityAlreadyExistsException("This movie is already exist");
        }
        Movie updateMovie = this.movieRepository.getById(movie_id);
        updateMovie.setMoviename(newMovie.getMoviename());
        updateMovie.setRuntime(newMovie.getRuntime());
        updateMovie.setReleasedate(newMovie.getReleasedate());
        updateMovie.setPlot(newMovie.getPlot());
        updateMovie.setAvg_rating(newMovie.getAvg_rating());
        updateMovie.setPoster(newMovie.getPoster());
        updateMovie.setTrailer(newMovie.getTrailer());
        updateMovie.setStudio(newMovie.getStudio());
        updateMovie.setStatus(newMovie.getStatus());
        updateMovie.setMovieGenre(newMovie.getMovieGenre());
        movieRepository.save(updateMovie);
    }


    @PostMapping("/movie/{id}/fav")
    public void favMovie(@PathVariable("id") int movie_id, Authentication auth) {
        Movie currentMovie = movieRepository.findById(movie_id).orElse(null);
        User currentUser = userService.getUserCurrent(auth);
        currentUser.setFav(currentMovie);
        userRepo.save(currentUser);
    }

    @DeleteMapping("/movie/fav/{id}")
    public void unFavMovie(@PathVariable("id") int movie_id, Authentication auth) {
        Movie currentMovie = movieRepository.findById(movie_id).orElse(null);
        User currentUser = userService.getUserCurrent(auth);
        currentUser.getUserFav().remove(currentMovie);
        userRepo.save(currentUser);
    }

}
