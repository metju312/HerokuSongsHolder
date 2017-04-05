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
        Song songW = new Song("Dżem - Whiskey",
                "Mówią o mnie w mieście - co z niego za typ\n" +
                "Wciąż chodzi pijany, pewno nie wie co to wstyd\n" +
                "Brudny, niedomytek - w stajnie ciągle śpi\n" +
                "Czego czukasz w naszym mieści\n" +
                "Idź do diabła - mówią ludzie pełni cnót\n" +
                "Ludzie pełni cnót\n" +
                "\n" +
                "Chciałem kiedyś zmądrzeć, po ich stronie być\n" +
                "Spać w czystej pościeli, świeże mleko pić\n" +
                "Naprwdę chciałem zmądrzeć i po ich stronie być\n" +
                "Pomyślałem więc o żonie aby stać się jednym z nich\n" +
                "Stać się jednym z nich\n" +
                "\n" +
                "Miałem na oku hacjendę - wspaniałą mówię wam\n" +
                "Lecz nie chciałe tam zamieszkać, żadna z pięknych dam\n" +
                "Wszystkie śmiały się wołając, wołając za mną wciąż\n" +
                "Bardzo ładny frak masz Billy, ale kiepski byłby z ciebie mąż\n" +
                "Kiepski byłby mąż\n" +
                "\n" +
                "Whisky - moja żono - jednak tyś najlepszą z dam\n" +
                "Już mnie nie opuścisz nie nie będę sam\n" +
                "Mówią - chisky to nie wszystko, można bez niej żyć\n" +
                "Lecz nie wiedzą o tym, że - że najgorzej to\n" +
                "To samotnym być, to samotnym być - nie\n" +
                "Nie chcę już samotnym być - nie... ", new Date());

        Link linkA = new Link("Alink", "http://www.google.pl",songA);
        Link linkAA = new Link("AAlink", "http://www.onet.pl",songA);
        Link linkB = new Link("Blink", "http://www.google.pl",songB);
        Link linkBB = new Link("BBlink", "http://www.google.pl",songB);
        Link linkW = new Link("tekst + tabs", "https://tabs.ultimate-guitar.com/tab/497416",songW);
        Link linkWW = new Link("tabs + solo", "http://www.chords.pl/chwyty/dzem/17741,wehikul_czasu_zagrywka",songW);

        YTData ytDataA = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songA);
        YTData ytDataAA = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songA);
        YTData ytDataB = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songB);
        YTData ytDataBB = new YTData("https://www.youtube.com/embed/nso6Vhg0p9k",songB);
        YTData ytDataW = new YTData("https://www.youtube.com/embed/dfQUANtgcx4",songW);
        YTData ytDataWW = new YTData("https://www.youtube.com/embed/Ldsyplj3Y-Q",songW);

        songRepository.save(songA);
        songRepository.save(songB);
        songRepository.save(songC);
        songRepository.save(songD);
        songRepository.save(songW);

        linkRepository.save(linkA);
        linkRepository.save(linkAA);
        linkRepository.save(linkB);
        linkRepository.save(linkBB);
        linkRepository.save(linkW);
        linkRepository.save(linkWW);

        ytDataRepository.save(ytDataA);
        ytDataRepository.save(ytDataAA);
        ytDataRepository.save(ytDataB);
        ytDataRepository.save(ytDataBB);
        ytDataRepository.save(ytDataW);
        ytDataRepository.save(ytDataWW);
    }
}