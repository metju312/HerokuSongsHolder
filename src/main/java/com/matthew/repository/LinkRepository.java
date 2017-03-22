package com.matthew.repository;

import com.matthew.model.Link;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LinkRepository extends PagingAndSortingRepository<Link, Long> {
}

