package int222.backend.controllers;


import int222.backend.models.entities.Authority;
import int222.backend.models.entities.Movie;
import int222.backend.models.entities.User;
import int222.backend.models.exceptions.EntityAlreadyExistsException;
import int222.backend.models.services.UserService;
import int222.backend.repositories.AuthorityRepository;
import int222.backend.repositories.MovieRepository;
import int222.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/view/author")
    public List<Authority> getAuthorityList(){
        return this.authorityRepository.findAll();
    }

    @GetMapping("/view/user")
    public List<User> getUserList(){
        return  this.userRepository.findAll();

    }
    @GetMapping("/view/user/{name}")
    public User getUserByFirstname(@PathVariable("name") String userFirstname){
        User user = this.userRepository.findByFirstnameIgnoreCase(userFirstname).orElse(null);
        if (user==null){
            throw new EntityNotFoundException();
        }
        return user;
    }


    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id)  {
       if(userRepository.findById(id).isEmpty()){
           return "Delete unsucessful";
       }
       userRepository.deleteById(id);
        return "Delete sucessful";
    }
//    @ExceptionHandler(EntityNotFoundException.class)
//    public void handleUserNotFound(EntityNotFoundException exception, HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
//    }
//
//    @ExceptionHandler(EntityAlreadyExistsException.class)
//    public void handleUserAlreadyExists(EntityAlreadyExistsException exception, HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.CONFLICT.value(), exception.getMessage());
//    }
@GetMapping("/user/favlist")
public  Set<Movie> getUserfav(Authentication auth){
//        String user = auth.getName();
        User currentUser = userService.getUserCurrent(auth);
        return currentUser.getUserFav();
}


}
