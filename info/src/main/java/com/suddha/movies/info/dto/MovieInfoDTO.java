package com.suddha.movies.info.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieInfoDTO {
    private String id;
    private String name;
    private List<String> genre;
    private Integer releaseYear;
}
