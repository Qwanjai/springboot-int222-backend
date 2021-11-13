package int222.backend.controllers;

import int222.backend.models.entities.Movie;
import int222.backend.models.entities.User;
import int222.backend.models.exceptions.EntityAlreadyExistsException;
import int222.backend.models.exceptions.ResourceNotFoundException;
import int222.backend.models.services.ImageService;
import int222.backend.models.services.MovieService;
import int222.backend.models.services.UserService;
import int222.backend.repositories.MovieRepository;
import int222.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
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
        return movieRepository.findAll();
    }


    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") int movieId) {
        Movie respone = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Not found Movie  with id : " + movieId));
       return new ResponseEntity<>(respone, HttpStatus.OK);
    }


    @GetMapping("/movie/name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName) {
        Movie respone = movieRepository.findByMovienameContainsIgnoreCase(movieName).orElseThrow(() -> new ResourceNotFoundException("Not found Movie  with name : " + movieName));
        return new ResponseEntity<>(respone, HttpStatus.OK);
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
    @PostMapping(value = "/admin/movie/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> newMovie(@RequestParam String movie, @RequestPart MultipartFile file) throws IOException,MultipartException {
        Movie newMovie = movieService.convertJsonStringToMovie(movie);
        if (movieService.checkNameIsAlreadyExists(newMovie.getMoviename())) {
            throw new EntityAlreadyExistsException("Movie is already exists");
        }
        newMovie.setPoster(file.getOriginalFilename());
        imageService.saveImg(file);
        movieRepository.save(newMovie);
        return  ResponseEntity.ok("Add movie successfully");
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/movie/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") int movieId) throws IOException{
        Movie delMovie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Not found Movie  with id: " + movieId));
        String delMovieImg = delMovie.getPoster();
        imageService.deleteImg(delMovieImg);
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return ResponseEntity.ok("Movie successfully deleted");
        } else {
            throw new ResourceNotFoundException("Not found this movie ");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value ="/admin/movie/edit/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") int movie_id, @RequestParam String movie, @RequestPart(required = false) MultipartFile file) throws IOException, MultipartException {
        Movie newMovie = movieService.convertJsonStringToMovie(movie);
        Movie oldMovie = movieRepository.findById(movie_id).orElseThrow(() -> new ResourceNotFoundException("Not found Movie  with name : " + newMovie.getMoviename()));
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
        imageService.saveImg(file);
        updateMovie.setTrailer(newMovie.getTrailer());
        updateMovie.setStudio(newMovie.getStudio());
        updateMovie.setStatus(newMovie.getStatus());
        updateMovie.setMovieGenre(newMovie.getMovieGenre());
        movieRepository.save(updateMovie);
        return ResponseEntity.ok().body(updateMovie);
    }


    @PostMapping("/movie/{id}/fav")
    public ResponseEntity<String> favMovie(@PathVariable("id") int movie_id, Authentication auth) {
        Movie currentMovie = movieRepository.findById(movie_id).orElse(null);
        if (currentMovie == null) {
            throw new EntityNotFoundException("Not found this movie ");
        }
        User currentUser = userService.getUserCurrent(auth);
        currentUser.setFav(currentMovie);
        userRepo.save(currentUser);
        return ResponseEntity.ok("Add to favorite list successfully");
    }

    @DeleteMapping("/movie/fav/{id}")
    public ResponseEntity<String> unFavMovie(@PathVariable("id") int movie_id, Authentication auth) {
        Movie currentMovie = movieRepository.findById(movie_id).orElse(null);
        if (currentMovie == null) {
            throw new EntityNotFoundException("Not found this movie ");
        }
        User currentUser = userService.getUserCurrent(auth);
        currentUser.getUserFav().remove(currentMovie);
        userRepo.save(currentUser);
        return ResponseEntity.ok("Remove from favorite list successfully");
    }

}
