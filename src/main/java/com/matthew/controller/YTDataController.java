package com.matthew.controller;

import com.matthew.model.Song;
import com.matthew.model.YTData;
import com.matthew.repository.SongRepository;
import com.matthew.repository.YTDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ytdatas")
@RestController
@ExposesResourceFor(YTData.class)
public class YTDataController{

    @Autowired
    YTDataRepository ytDataRepository;

    @GetMapping
    public List<YTData> findAll() {
        return (List<YTData>) ytDataRepository.findAll();
    }

    @PostMapping
    public YTData create(@RequestBody YTData ytData) {
        return ytDataRepository.save(ytData);
    }

    @GetMapping("/{id}")
    public YTData findOne(@PathVariable int id) {
        return ytDataRepository.findOne((long) id);
    }

    @PutMapping
    public YTData update(@RequestBody YTData ytData) {
        return ytDataRepository.save(ytData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        ytDataRepository.delete((long) id);
    }
}
