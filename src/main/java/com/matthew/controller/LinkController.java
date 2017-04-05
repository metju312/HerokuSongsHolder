package com.matthew.controller;

import com.matthew.model.Link;
import com.matthew.model.Song;
import com.matthew.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/links")
@RestController
@ExposesResourceFor(Link.class)
public class LinkController{

    @Autowired
    LinkRepository linkRepository;

    @GetMapping
    public List<Link> findAll() {
        return (List<Link>) linkRepository.findAll();
    }

    @PostMapping
    public Link create(@RequestBody Link link) {
        return linkRepository.save(link);
    }

    @GetMapping("/{id}")
    public Link findOne(@PathVariable int id) {
        return linkRepository.findOne((long) id);
    }

    @PutMapping
    public Link update(@RequestBody Link link) {
        return linkRepository.save(link);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        linkRepository.delete((long) id);
    }

//    @PostMapping("/song")
//    public List<Link> findBySong(@RequestParam("song") Song song) {
//        return linkRepository.findBySong(song);
//    }
    @PostMapping("/song")
    public List<Link> findBySong(@RequestBody Song song) {
        return linkRepository.findBySong(song);
    }
}