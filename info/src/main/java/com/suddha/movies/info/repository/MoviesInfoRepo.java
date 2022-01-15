package com.suddha.movies.info.repository;

import com.suddha.movies.info.domain.MoviesInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesInfoRepo extends ReactiveMongoRepository<MoviesInfo,String> {
}
