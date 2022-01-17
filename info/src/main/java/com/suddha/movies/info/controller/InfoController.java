package com.suddha.movies.info.controller;

import com.suddha.movies.info.domain.Genre;
import com.suddha.movies.info.dto.MovieInfoDTO;
import com.suddha.movies.info.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Duration;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/${api.name}/${api.version}/${api.resource}")
@Slf4j
public class InfoController {

    private final Flux<MovieInfoDTO> moviesInfoFlux;
    private final InfoService infoService;

    @Autowired
    public InfoController(Flux<MovieInfoDTO> moviesInfoFlux, InfoService infoService) {
        this.moviesInfoFlux = moviesInfoFlux;
        this.infoService = infoService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieInfoDTO> allMovies() {
        return infoService.findAll();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieInfoDTO> streamMovies() {
        return Flux.interval(Duration.ofSeconds(5))
                .flatMap(ignore -> infoService.findAll().delayElements(Duration.ofSeconds(1)));
    }

    @GetMapping(value = "/stream/updates", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieInfoDTO> streamMoviesUpdates() {
        return moviesInfoFlux;
    }

    @GetMapping("/{id}")
    public Mono<MovieInfoDTO> moviesById(@PathVariable String id) {
        return infoService.findById(id);
    }

    @PostMapping
    public Mono<MovieInfoDTO> save(@RequestBody MovieInfoDTO moviesInfo) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<MovieInfoDTO>> violations = validator.validate(moviesInfo);
        for (ConstraintViolation<MovieInfoDTO> violation : violations) {
            log.error(violation.getMessage());
        }
        return infoService.save(Mono.just(moviesInfo));
    }

    @GetMapping
    public Flux<MovieInfoDTO> findByGenre(@RequestParam(value = "genres", required = false, defaultValue = "") List<Genre> genres) {
        return infoService.findByGenre(genres);
    }

}
