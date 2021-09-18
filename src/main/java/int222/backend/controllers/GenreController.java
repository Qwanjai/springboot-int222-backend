package int222.backend.controllers;


import int222.backend.models.Genre;
import int222.backend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genre")
    public List<Genre> getGenreList(){
        return  this.genreRepository.findAll();
    }

    @GetMapping("/view")
    public String  getViewTest(){
        return  "this is a test";
    }
}
