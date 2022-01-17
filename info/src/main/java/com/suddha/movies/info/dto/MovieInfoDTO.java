package com.suddha.movies.info.dto;

import com.suddha.movies.info.domain.Genre;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class MovieInfoDTO {

    @NotNull(message = "Movie ID cannot be null")
    @NotEmpty(message = "Movie ID cannot be empty")
    private String id;

    @NotNull(message = "Movie name cannot be null")
    @NotEmpty(message = "Movie name cannot be empty")
    @Size(min = 2, max = 24, message = "Movie name must have minimum of 2 characters and maximum of 24 characters")
    private String name;


    @NotNull(message = "Movie genre cannot be null")
    @NotEmpty(message = "Movie genre cannot be empty")
    private List<Genre> genre;

    @NotNull(message = "Movie release year cannot be null")
    @Min(value = 1900, message = "Release year must be greater than 1900")
    private Integer releaseYear;

}
