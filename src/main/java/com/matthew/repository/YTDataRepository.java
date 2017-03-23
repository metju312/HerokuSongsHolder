package com.matthew.repository;

import com.matthew.model.Song;
import com.matthew.model.YTData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface YTDataRepository extends PagingAndSortingRepository<YTData, Long> {
    List<YTData> findBySong(@Param("song") Song song);
}

