package com.suddha.movies.info.dto;

import com.suddha.movies.info.domain.Genre;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class MovieInfoDTO {

    @NotBlank(message = "Movie ID cannot be null")
    @NotEmpty(message = "Movie ID cannot be empty")
    private String id;

    @NotBlank(message = "Movie name cannot be null")
    @NotEmpty(message = "Movie name cannot be empty")
    @Min(value = 2, message = "Movie name must have minimum of 2 characters")
    private String name;


    @NotBlank(message = "Movie genre cannot be null")
    @NotEmpty(message = "Movie genre cannot be empty")
    private List<Genre> genre;

    @NotBlank(message = "Movie release year cannot be null")
    @NotEmpty(message = "Movie release year cannot be empty")
    @Min(value = 1900, message = "Release year must be greater than 1900")
    private Integer releaseYear;

}
