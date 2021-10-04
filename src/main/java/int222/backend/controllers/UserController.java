package int222.backend.controllers;


import int222.backend.models.entities.Authority;
import int222.backend.models.entities.User;
import int222.backend.models.exceptions.EntityAlreadyExistsException;
import int222.backend.models.exceptions.UserAlreadyExistException;
import int222.backend.repositories.AuthorityRepository;
import int222.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//List<Authority> authorAll = this.authorityRepository.findAll()

    @GetMapping("/view/author")
    public List<Authority> getAuthorityList(){
        return this.authorityRepository.findAll();
    }

    @GetMapping("/view/user")
    public List<User> getUserList(){
        return  this.userRepository.findAll();

    }
//
//    @GetMapping("/view/user/{id}")
//    public User getUser(@PathVariable("id") long id)throws EntityNotFoundException{
//        return  this.userRepository.findById(id);
//
//    }



    @PostMapping("/signup")
    public void createNewUser(@RequestBody User user)throws EntityAlreadyExistsException {
            Authority roleUser= authorityRepository.findById(2);
            user.setRole(roleUser);
            userRepository.save(user);
//        User newUser= userRepository.save(user);
//       new  newUser.setRole(roleUser);
//        newUser.setRole(getAuthorityList().get(0));
//        return newUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id)  {
       if(userRepository.findById(id).isEmpty()==false){
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

//    private void isValidUserId(long id) throws EntityNotFoundException {
//        User user = userService.findOne(id);
//        if (user == null) {
//            throw new EntityNotFoundException("User with " + id + "not found!");
//        }
//    }

}
