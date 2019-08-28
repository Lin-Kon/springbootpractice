package com.example.springbootpractice.model;

import com.sun.org.glassfish.gmbal.ParameterNames;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
@Entity
public class Movie {
    @Id
    private long id;
    private String title;
}
