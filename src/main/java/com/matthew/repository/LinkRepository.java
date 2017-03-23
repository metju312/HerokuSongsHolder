package com.matthew.repository;

import com.matthew.model.Link;
import com.matthew.model.Song;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LinkRepository extends PagingAndSortingRepository<Link, Long> {
    List<Link> findBySong(@Param("song") Song song);
}

