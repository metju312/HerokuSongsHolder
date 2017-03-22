package com.matthew.repository;

import com.matthew.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
}
