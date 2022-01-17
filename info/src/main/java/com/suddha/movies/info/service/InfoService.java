package com.suddha.movies.info.service;

import com.suddha.movies.info.domain.Genre;
import com.suddha.movies.info.dto.MovieInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface InfoService {

    Flux<MovieInfoDTO> findAll();

    Mono<MovieInfoDTO> findById(String id);

    Mono<MovieInfoDTO> save(Mono<MovieInfoDTO> moviesInfo);

    Flux<MovieInfoDTO> findByGenre(List<Genre> genres);

}
