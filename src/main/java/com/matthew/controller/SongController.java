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

@RequestMapping("/song")
@RestController
@ExposesResourceFor(Song.class)
public class SongController implements ResourceProcessor<RepositoryLinksResource> {

    @Autowired
    SongRepository songRepository;


    @RequestMapping("/welcome")
    public String hello(Model model) {
        System.out.println("Welcome Friends!");
        model.addAttribute("msg", "Welcome Friends!");
        return "result";
    }

    @ResponseBody
    @RequestMapping(value = "/al", method = RequestMethod.GET, produces = "application/json")
    public String getAllSongs() {
        Iterable<Song> songs= songRepository.findAll();
        return "Hello";
    }


    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Song> listEntities(
            Pageable pageable) throws ResourceNotFoundException {
        Iterable<Song> songs= songRepository.findAll();
        return songs;
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(SongController.class).withRel("others"));
        return resource;
    }
}