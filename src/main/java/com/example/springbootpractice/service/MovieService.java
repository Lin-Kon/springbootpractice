package com.example.springbootpractice.service;

import com.example.springbootpractice.exception.ResourceAlreadyExistException;
import com.example.springbootpractice.exception.ResourceDoesNotExistException;
import com.example.springbootpractice.model.Movie;
import com.example.springbootpractice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository= movieRepository;
    }

    public Movie insertMovie(Movie movie) throws ResourceAlreadyExistException {
        Optional<Movie> optionalMovie = movieRepository.findById(movie.getId());
        if(optionalMovie.isPresent()) {
            throw new ResourceAlreadyExistException(movie.getId()+"");
        } else {
            return movieRepository.save(movie);
        }
    }

    public Movie findById(long id) throws ResourceDoesNotExistException {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()){
            return optionalMovie.get();
        } else throw new ResourceDoesNotExistException(id + "");
    }

//    public Movie findByTitle(String title) throws ResourceDoesNotExistException {
//        Optional<Movie> optionalMovie = movieRepository.findByName();
//        if(optionalMovie.isPresent()){
//            return optionalMovie.get();
//        } else throw new ResourceDoesNotExistException(title);
//    }

    public List<Movie> findAll() {
        List<Movie> movieList = new ArrayList<>();
        movieRepository.findAll().forEach(movieList::add);
        return movieList;
    }

    public Movie updateMovie(Movie movie) throws ResourceDoesNotExistException {
        Optional<Movie> optionalMovie = movieRepository.findById(movie.getId());
        if(optionalMovie.isPresent()) {
            return movieRepository.save(movie);
        } else {
            throw new ResourceDoesNotExistException(movie.getId() + "");
        }
    }

    public boolean deleteById(long id) throws ResourceDoesNotExistException {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        optionalMovie.ifPresent(movie -> movieRepository.deleteById(id));
        optionalMovie.orElseThrow(() -> new ResourceDoesNotExistException(id + ""));
        return true;
    }
}
