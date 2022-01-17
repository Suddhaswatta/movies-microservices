package com.suddha.movies.info.config;

import com.suddha.movies.info.dto.MovieInfoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class SinksConfig {

    @Bean
    public Flux<MovieInfoDTO> stream(Sinks.Many<MovieInfoDTO> sinks) {
        return sinks.asFlux();
    }

    @Bean
    public Sinks.Many<MovieInfoDTO> sinks() {
        return Sinks.many().replay().limit(1);
    }
}
