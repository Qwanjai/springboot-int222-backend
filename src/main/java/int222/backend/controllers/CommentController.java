package int222.backend.controllers;


import int222.backend.models.entities.Comment;
import int222.backend.models.entities.Movie;
import int222.backend.models.entities.User;
import int222.backend.models.services.UserService;
import int222.backend.repositories.CommentRepository;
import int222.backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private MovieRepository movieRepo;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();



    @PostMapping("/add/{id}")
    public void createComment(@PathVariable("id") int movieId,@RequestBody Comment newComment,Authentication auth){
      Movie currentMovie = movieRepo.findById(movieId).orElse(null);
      User currentUser = userService.getUserCurrent(auth);
        String currentDate = formatter.format(date);
        newComment.setCommentid(currentMovie.getMovie_id(),currentUser.getUser_id());
        newComment.setCreate_date(currentDate);
       newComment.setMovie(currentMovie);
       newComment.setUser(currentUser);
       commentRepo.save(newComment);

    }


}
