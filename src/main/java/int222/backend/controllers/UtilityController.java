package int222.backend.controllers;


import int222.backend.models.entities.*;
import int222.backend.models.services.UserService;
import int222.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UtilityController {
    @Autowired
    private GenreRepository genreRepository;


    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private MovieStatusRepository movieStatusRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view/genre")
    public List<Genre> getGenreList() {
        return this.genreRepository.findAll();
    }

    @GetMapping("/test")
    public String getViewTest() {
        return "this is a test";
    }

    @DeleteMapping("/genre/{id}")
    public String deleteGenre(@PathVariable("id") int id) {
        genreRepository.deleteById(id);
        if (genreRepository.findById(id).isEmpty() == false) {
            return "Delete Genre Unsucessful";
        }
        return "Delete Genre Sucessful";
    }

    @GetMapping("/view/studio")
    public List<Studio> getStudioList() {
        return this.studioRepository.findAll();
    }

    @GetMapping("/view/moviestatus")
    public List<Status> getMovieStatusList() {
        return this.movieStatusRepository.findAll();
    }

    @GetMapping("/test/author")
    public String testAuthorityPass(){
        return "if you see this it means you are authorized";
    }

    @GetMapping("/view/comment")
    public List<Comment> getCommentList(){
        return commentRepository.findAll();
    }

    @GetMapping("/view/date")
    public String getDate(){
        String currentDate = formatter.format(date);
        return currentDate;
    }
    @GetMapping("/view")
    public User getUser(Authentication auth){
        User currentUser = userService.getUserCurrent(auth);
        return currentUser;
    }

}
