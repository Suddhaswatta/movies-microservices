package com.suddha.movies.info.repository;

import com.suddha.movies.info.domain.MoviesInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MoviesInfoRepo extends ReactiveMongoRepository<MoviesInfo, String> {
    Flux<MoviesInfo> findByReleaseYear(Integer releaseYear);
}
