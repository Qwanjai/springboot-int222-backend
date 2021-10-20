package int222.backend.controllers;


import int222.backend.models.entities.Authority;
import int222.backend.models.entities.User;
import int222.backend.models.exceptions.EntityAlreadyExistsException;
import int222.backend.repositories.AuthorityRepository;
import int222.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    @GetMapping("/view/author")
    public List<Authority> getAuthorityList(){
        return this.authorityRepository.findAll();
    }

    @GetMapping("/view/user")
    public List<User> getUserList(){
        return  this.userRepository.findAll();

    }


    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id)  {
       if(userRepository.findById(id).isEmpty()){
           return "Delete unsucessful";
       }
       userRepository.deleteById(id);
        return "Delete sucessful";
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleUserNotFound(EntityNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public void handleUserAlreadyExists(EntityAlreadyExistsException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(), exception.getMessage());
    }



}
