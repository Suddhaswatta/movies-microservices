package com.suddha.movies.info.controller;

import com.suddha.movies.info.domain.Genre;
import com.suddha.movies.info.domain.MoviesInfo;
import com.suddha.movies.info.dto.MovieInfoDTO;
import com.suddha.movies.info.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/${api.name}/${api.version}/${api.resource}")
public class InfoController {

    @Autowired
    Flux<MoviesInfo> moviesInfoFlux;

    @Autowired
    private InfoService infoService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MoviesInfo> allMovies() {
        return infoService.findAll();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MoviesInfo> streamMovies() {
        return moviesInfoFlux;
    }

    @GetMapping("/{id}")
    public Mono<MoviesInfo> moviesById(@PathVariable String id) {
        return infoService.findById(id);
    }

    @PostMapping
    public Mono<MoviesInfo> save(@RequestBody MovieInfoDTO moviesInfo) {
        return infoService.save(Mono.just(moviesInfo));
    }

    @GetMapping
    public Flux<MovieInfoDTO> findByGenre(@RequestParam(value = "genres", required = false) List<Genre> genres) {
        return infoService.findByGenre(genres);
    }

}
