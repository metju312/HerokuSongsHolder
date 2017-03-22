package com.matthew.config;



import com.matthew.model.Link;
import com.matthew.model.Song;
import com.matthew.model.YTData;
import com.matthew.repository.LinkRepository;
import com.matthew.repository.SongRepository;
import com.matthew.repository.YTDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
public class RestConfig extends RepositoryRestMvcConfiguration {

    @Autowired
    SongRepository songRepository;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    YTDataRepository ytDataRepository;



    @PostConstruct
    public void init() {
        Song songA = new Song("Asong", "", new Date());
        Song songB = new Song("Bsong", "B", new Date());
        Song songC = new Song("Csong", "CC", new Date());
        Song songD = new Song("Dsong", "DDD", new Date());

        Link linkA = new Link("Alink", "http://www.google.pl",songA);
        Link linkAA = new Link("AAlink", "http://www.onet.pl",songA);
        Link linkB = new Link("Blink", "http://www.google.pl",songB);
        Link linkBB = new Link("BBlink", "http://www.google.pl",songB);

        YTData ytDataA = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songA);
        YTData ytDataAA = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songA);
        YTData ytDataB = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songB);
        YTData ytDataBB = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songB);

        songRepository.save(songA);
        songRepository.save(songB);
        songRepository.save(songC);
        songRepository.save(songD);

        linkRepository.save(linkA);
        linkRepository.save(linkAA);
        linkRepository.save(linkB);
        linkRepository.save(linkBB);

        ytDataRepository.save(ytDataA);
        ytDataRepository.save(ytDataAA);
        ytDataRepository.save(ytDataB);
        ytDataRepository.save(ytDataBB);
    }
}