package com.suddha.movies.info.service;

import com.suddha.movies.info.domain.MoviesInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InfoService {

    Flux<MoviesInfo> findAll();

    Mono<MoviesInfo> findById(String id);

    Mono<MoviesInfo> save(Mono<MoviesInfo> moviesInfo);

}
