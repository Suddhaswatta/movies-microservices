package com.suddha.movies.info.dto;

import com.suddha.movies.info.domain.Genre;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class MovieInfoDTO {

    @NotBlank(message = "Movie ID cannot be blank")
    @Positive
    private String id;

    @NotBlank(message = "Movie name cannot be blank")
    @Size(min = 2, max = 24, message = "Movie name must have minimum of 2 characters and maximum of 24 characters")
    private String name;

    private List<@NotEmpty(message = "Movie genre cannot be empty") Genre> genre;

    @NotBlank(message = "Movie release year cannot be blank")
    @Min(value = 1900, message = "Release year must be greater than 1900")
    private Integer releaseYear;

}
