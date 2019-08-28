package com.example.springbootpractice.repository;

import com.example.springbootpractice.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
List<Movie> findAllByTitle(String title);
List<Movie> findAllByTitleContaining(String partialTitle);
Optional<Movie> findByName(String title);
}
