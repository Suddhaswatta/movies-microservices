package com.suddha.movies.info.service;

import com.suddha.movies.info.utils.Utils;
import com.suddha.movies.info.domain.MoviesInfo;
import com.suddha.movies.info.dto.MovieInfoDTO;
import com.suddha.movies.info.repository.MoviesInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    Sinks.Many<MoviesInfo> moviesInfoMany;
    @Autowired
    private MoviesInfoRepo moviesInfoRepo;

    @Override
    public Flux<MoviesInfo> findAll() {
        return moviesInfoRepo.findAll().log();
    }

    @Override
    public Mono<MoviesInfo> findById(String id) {
        return moviesInfoRepo.findById(id).log();
    }

    @Override
    public Mono<MoviesInfo> save(Mono<MovieInfoDTO> movieInfoDTOMono) {
        return movieInfoDTOMono
                .map(Utils::toMovie)
                .flatMap(moviesInfoRepo::save)
                .doOnNext(moviesInfoMany::tryEmitNext)
                .log();
    }

    @Override
    public Flux<MovieInfoDTO> findByGenre(List<String> genres) {
        return moviesInfoRepo
                .findAll()
                .filter(moviesInfo ->
                        moviesInfo.getGenre().containsAll(genres)
                ).map(Utils::toDTO);
    }


}
