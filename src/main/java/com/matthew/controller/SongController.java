package com.matthew.controller;

import com.matthew.model.Song;
import com.matthew.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/songs")
@RestController
@ExposesResourceFor(Song.class)
public class SongController{

    @Autowired
    SongRepository songRepository;

    @GetMapping
    public List<Song> findAll() {
        return (List<Song>) songRepository.findAll();
    }

    @PostMapping
    public Song create(@RequestBody Song song) {
        return songRepository.save(song);
    }

    @GetMapping("/{id}")
    public Song findOne(@PathVariable int id) {
        return songRepository.findOne((long) id);
    }

    @PutMapping
    public Song update(@RequestBody Song song) {
        return songRepository.save(song);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        songRepository.delete((long) id);
    }
}