package com.suddha.movies.info.controller;

import com.suddha.movies.info.domain.Genre;
import com.suddha.movies.info.domain.MoviesInfo;
import com.suddha.movies.info.dto.MovieInfoDTO;
import com.suddha.movies.info.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/${api.name}/${api.version}/${api.resource}")
@Slf4j
public class InfoController {


    private final Flux<MoviesInfo> moviesInfoFlux;


    private final InfoService infoService;


    @Autowired
    public InfoController(Flux<MoviesInfo> moviesInfoFlux, InfoService infoService) {
        this.moviesInfoFlux = moviesInfoFlux;
        this.infoService = infoService;
    }

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
    public Mono<MoviesInfo> save(@RequestBody @Valid MovieInfoDTO moviesInfo) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<MovieInfoDTO>> violations = validator.validate(moviesInfo);
        for (ConstraintViolation<MovieInfoDTO> violation : violations) {
            log.error(violation.getMessage());
        }
        return infoService.save(Mono.just(moviesInfo));
    }

    @GetMapping
    public Flux<MovieInfoDTO> findByGenre(@RequestParam(value = "genres", required = false,defaultValue = "") List<Genre> genres) {
        return infoService.findByGenre(genres);
    }

}
