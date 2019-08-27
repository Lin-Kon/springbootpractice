package com.example.springbootpractice.controller;

import com.example.springbootpractice.exception.ResourceDoesNotExistException;
import com.example.springbootpractice.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/movies")
public class MovieController {
//    private MovieService movieService;

    private Map<Long, Movie> movieMap;

    public MovieController() {
        movieMap = new HashMap<>();
        movieMap.put(1000l, new Movie(1000l, "The American Virgin"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id){
        try{
//            movieMap.forEach((key, value) -> System.out.println(key + " : "+ value));
            Movie movie = movieMap.get(id);
            return ResponseEntity.ok(movie);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Movie>> getMovies(){
        List<Movie> movieList = movieMap.values().stream().collect(Collectors.toList());
        return ResponseEntity.ok(movieList);
    }

    @PostMapping("")
    public ResponseEntity<Movie> insertMovie(@RequestBody Movie movie)
    {
        try{
            movieMap.put(movie.getId(),movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        } catch(Exception e){
//            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteMovie(@PathVariable long id){
        try{
                if(movieMap.get(id)==null)
                   throw new ResourceDoesNotExistException(id + "");
                movieMap.remove(id);
                boolean deleted = true;
                return ResponseEntity.ok(id);
        } catch (ResourceDoesNotExistException e){
//            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
