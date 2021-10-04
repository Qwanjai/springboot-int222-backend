package int222.backend.controllers;


import int222.backend.models.entities.Genre;
import int222.backend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/view/genre")
    public List<Genre> getGenreList(){
        return  this.genreRepository.findAll();
    }

    @GetMapping("/test")
    public String  getViewTest(){
        return  "this is a test";
    }
    @DeleteMapping("/genre/{id}")
    public String deleteGenre(@PathVariable("id") int id){
        genreRepository.deleteById(id);
        if(genreRepository.findById(id).isEmpty()==false){
            return "Delete Genre Unsucessful";
        }
        return "Delete Genre Sucessful";
    }
}
