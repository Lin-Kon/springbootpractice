package com.example.springbootpractice.repository;

import com.example.springbootpractice.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
List<Movie> findAllByTitle(String title);
List<Movie> findAllByTitleContaining(String partialTitle);
}
