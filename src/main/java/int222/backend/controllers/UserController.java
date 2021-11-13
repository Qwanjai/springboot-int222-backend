package int222.backend.controllers;


import int222.backend.models.entities.Authority;
import int222.backend.models.entities.Movie;
import int222.backend.models.entities.User;

import int222.backend.models.services.UserService;
import int222.backend.repositories.AuthorityRepository;

import int222.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    private UserService userService;

    @GetMapping("/view/author")
    public List<Authority> getAuthorityList() {
        return this.authorityRepository.findAll();
    }

    @GetMapping("/view/user")
    public List<User> getUserList() {
        return this.userRepository.findAll();

    }

    @GetMapping("/view/user/{name}")
    public User getUserByFirstname(@PathVariable("name") String userFirstname) {
        User user = this.userRepository.findByFirstnameIgnoreCase(userFirstname).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Not found user with this id " + id);
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("Delete Sucsessful");
    }

    @GetMapping("/user/favlist")
    public Set<Movie> getUserfav(Authentication auth) {
        User currentUser = userService.getUserCurrent(auth);
        return currentUser.getUserFav();
    }


}
