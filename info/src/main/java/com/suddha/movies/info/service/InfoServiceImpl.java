package com.suddha.movies.info.service;

import com.suddha.movies.info.domain.Genre;
import com.suddha.movies.info.dto.MovieInfoDTO;
import com.suddha.movies.info.repository.MoviesInfoRepo;
import com.suddha.movies.info.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
@Slf4j
public class InfoServiceImpl implements InfoService {

    private final Sinks.Many<MovieInfoDTO> moviesInfoMany;
    private final MoviesInfoRepo moviesInfoRepo;

    @Autowired
    public InfoServiceImpl(Sinks.Many<MovieInfoDTO> moviesInfoMany, MoviesInfoRepo moviesInfoRepo) {
        this.moviesInfoMany = moviesInfoMany;
        this.moviesInfoRepo = moviesInfoRepo;
    }

    @Override
    public Flux<MovieInfoDTO> findAll() {
        return moviesInfoRepo.findAll().map(DataUtils::toDTO).log();
    }

    @Override
    public Mono<MovieInfoDTO> findById(String id) {

        return Mono.fromSupplier(() -> id)
                .flatMap(moviesInfoRepo::findById)
                .map(DataUtils::toDTO)
                .doOnNext(movieInfoDTO -> log.debug("Found Movie:{} by id : {}", movieInfoDTO, id))
                .log();
    }

    @Override
    public Mono<MovieInfoDTO> save(Mono<MovieInfoDTO> movieInfoDTOMono) {
        return movieInfoDTOMono
                .map(DataUtils::toMovie)
                .flatMap(moviesInfoRepo::save)
                .map(DataUtils::toDTO)
                .doOnNext(moviesInfoMany::tryEmitNext)
                .log();
    }


    @Override
    public Flux<MovieInfoDTO> findByGenre(List<Genre> genres) {

        return Mono
                .just(genres)
                .flatMapMany
                        (genresList -> moviesInfoRepo
                                .findAll()
                                .filter(moviesInfo -> moviesInfo.getGenre()
                                        .containsAll(genresList))
                        ).map(DataUtils::toDTO)
                .log();
    }

    @Override
    public Flux<MovieInfoDTO> findByReleaseYear(Integer year) {
        return Mono.fromSupplier(() -> year)
                .flatMapMany(moviesInfoRepo::findByReleaseYear)
                .map(DataUtils::toDTO);
    }


}
