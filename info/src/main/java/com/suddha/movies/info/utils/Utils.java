package com.suddha.movies.info.utils;

import com.suddha.movies.info.domain.MoviesInfo;
import com.suddha.movies.info.dto.MovieInfoDTO;

import static org.springframework.beans.BeanUtils.copyProperties;

public class Utils {

    private Utils() {
    }

    public static MoviesInfo toMovie(MovieInfoDTO movieInfoDTO ){
        MoviesInfo moviesInfo = new MoviesInfo();
        copyProperties(movieInfoDTO, moviesInfo);
        return moviesInfo;
    }

    public static MovieInfoDTO toDTO(MoviesInfo movieInfo){
        MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
        copyProperties(movieInfo, movieInfoDTO);
        return movieInfoDTO;
    }
}
