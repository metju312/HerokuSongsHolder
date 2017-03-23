package com.matthew.repository;

import com.matthew.model.Song;
import com.matthew.model.YTData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface SongRepository extends PagingAndSortingRepository<Song, Long>{
    List<Song> findByTitle(@Param("title") String title);
}
