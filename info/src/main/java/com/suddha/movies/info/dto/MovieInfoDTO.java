package com.suddha.movies.info.dto;

import com.suddha.movies.info.domain.Genre;
import lombok.Data;

import java.util.List;

@Data
public class MovieInfoDTO {
    private String id;
    private String name;
    private List<Genre> genre;
    private Integer releaseYear;

}
