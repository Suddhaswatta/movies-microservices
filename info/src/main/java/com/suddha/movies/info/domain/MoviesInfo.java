package com.suddha.movies.info.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesInfo {

    @Id
    private String id;
    private String name;
    private List<String> genre;
    private Integer releaseYear;
}
