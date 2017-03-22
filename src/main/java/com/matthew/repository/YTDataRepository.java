package com.matthew.repository;

import com.matthew.model.YTData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface YTDataRepository extends PagingAndSortingRepository<YTData, Long> {
}

