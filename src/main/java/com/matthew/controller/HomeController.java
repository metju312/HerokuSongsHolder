package com.matthew.controller;

import com.matthew.model.Song;
import com.matthew.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

//@Controller
public class HomeController {


//    @RequestMapping(value="/",method = RequestMethod.GET)
//    public String homepage(){
//        return "index";
//    }


//    @RequestMapping("/")
//    String home(ModelMap modal) {
//        modal.addAttribute("title","CRUD Example");
//        return "index";
//    }
//
//    @RequestMapping("/partial/{page}")
//    String partialHandler(@PathVariable("page") final String page) {
//        return page;
//    }
//    private SongRepository repository;
//
//    @Autowired
//    public HomeController(SongRepository repository) {
//        this.repository = repository;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String home(ModelMap model) {
//        Iterable<Song> songs = repository.findAll();
//        model.addAttribute("songs", songs);
//        model.addAttribute("insertSong", new Song());
//        return "home";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String insertData(ModelMap model,
//                             @ModelAttribute("insertSong") @Valid Song song,
//                             BindingResult result) {
//        if (!result.hasErrors()) {
//            repository.save(song);
//        }
//        return home(model);
//    }
}