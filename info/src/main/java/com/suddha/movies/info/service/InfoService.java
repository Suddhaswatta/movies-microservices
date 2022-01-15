package com.suddha.movies.info.service;

import com.suddha.movies.info.domain.MoviesInfo;
import com.suddha.movies.info.dto.MovieInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InfoService {

    Flux<MoviesInfo> findAll();

    Mono<MoviesInfo> findById(String id);

    Mono<MoviesInfo> save(Mono<MovieInfoDTO> moviesInfo);

}
