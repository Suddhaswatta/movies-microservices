package com.suddha.movies.info.controller;

import com.suddha.movies.info.domain.MoviesInfo;
import com.suddha.movies.info.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${api.name}/${api.version}/${api.resource}")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private Flux<MoviesInfo> stream;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MoviesInfo> allMovies() {
        return infoService.findAll();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MoviesInfo> streamMovies() {
        return stream;
    }

    @GetMapping("/{id}")
    public Mono<MoviesInfo> moviesById(@PathVariable String id) {
        return infoService.findById(id);
    }

    @PostMapping
    public Mono<MoviesInfo> save(@RequestBody MoviesInfo moviesInfo) {
        return infoService.save(Mono.just(moviesInfo));
    }


}
