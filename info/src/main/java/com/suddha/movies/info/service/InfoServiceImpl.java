package com.suddha.movies.info.service;

import com.suddha.movies.info.domain.MoviesInfo;
import com.suddha.movies.info.repository.MoviesInfoRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InfoServiceImpl implements InfoService {

    private final MoviesInfoRepo moviesInfoRepo;

    public InfoServiceImpl(MoviesInfoRepo moviesInfoRepo) {
        this.moviesInfoRepo = moviesInfoRepo;
    }

    @Override
    public Flux<MoviesInfo> findAll() {
        return moviesInfoRepo.findAll().log();
    }

    @Override
    public Mono<MoviesInfo> findById(String id) {
        return moviesInfoRepo.findById(id).log();
    }

    @Override
    public Mono<MoviesInfo> save(Mono<MoviesInfo> moviesInfo) {
        return moviesInfo
                .flatMap(moviesInfoRepo::save)
                .log();
    }


}
