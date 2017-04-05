package com.matthew.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String title;

    @Column(length = 2048)
    private String lyrics;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "song", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<YTData> ytDatas = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "song", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Link> links = new ArrayList<>();

    public Song() {
    }

    public Song(String title, String lyrics, Date date) {
        this.title = title;
        this.lyrics = lyrics;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<YTData> getYtDatas() {
        return ytDatas;
    }

    public void setYtDatas(List<YTData> ytDatas) {
        this.ytDatas = ytDatas;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
