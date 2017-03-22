package com.matthew.service;

import com.matthew.model.Song;

import java.util.List;

public interface SongService {

    Song findById(long id);

    Song findByTitle(String title);

    void save(Song song);

    void update(Song song);

    void deleteById(long id);

    List<Song> findAll();

    void deleteAll();

    public boolean isExist(Song song);

}
