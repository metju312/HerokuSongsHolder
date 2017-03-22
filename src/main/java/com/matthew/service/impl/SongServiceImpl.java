package com.matthew.service.impl;

import com.matthew.model.Song;
import com.matthew.service.SongService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("songService")
@Transactional
public class SongServiceImpl implements SongService {

    @Override
    public Song findById(long id) {
        return null;
    }

    @Override
    public Song findByTitle(String title) {
        return null;
    }

    @Override
    public void save(Song song) {

    }

    @Override
    public void update(Song song) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean isExist(Song song) {
        return false;
    }
}