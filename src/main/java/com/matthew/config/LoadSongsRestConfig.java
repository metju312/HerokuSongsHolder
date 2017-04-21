package com.matthew.config;

import com.matthew.model.Link;
import com.matthew.model.Song;
import com.matthew.model.YTData;
import com.matthew.repository.LinkRepository;
import com.matthew.repository.SongRepository;
import com.matthew.repository.YTDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Configuration
public class LoadSongsRestConfig extends RepositoryRestMvcConfiguration {
    @Autowired
    SongRepository songRepository;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    YTDataRepository ytDataRepository;

    @PostConstruct
    public void init() {
        loadTestData();
//        loadSongs("C:\\Projekty\\Heroku\\songs-holder\\songs");
        loadSongs("songs");
    }

    public void loadTestData() {
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

        Link linkA = new Link("Alink", "http://www.google.pl", songA);
        Link linkAA = new Link("AAlink", "http://www.onet.pl", songA);
        Link linkB = new Link("Blink", "http://www.google.pl", songB);
        Link linkBB = new Link("BBlink", "http://www.google.pl", songB);
        Link linkW = new Link("tekst + tabs", "https://tabs.ultimate-guitar.com/tab/497416", songW);
        Link linkWW = new Link("tabs + solo", "http://www.chords.pl/chwyty/dzem/17741,wehikul_czasu_zagrywka", songW);

        YTData ytDataA = new YTData("nso6Vhg0p9k", songA);
        YTData ytDataAA = new YTData("nso6Vhg0p9k", songA);
        YTData ytDataB = new YTData("nso6Vhg0p9k", songB);
        YTData ytDataBB = new YTData("nso6Vhg0p9k", songB);
        YTData ytDataW = new YTData("dfQUANtgcx4", songW);
        YTData ytDataWW = new YTData("Ldsyplj3Y-Q", songW);

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

    public void loadSongs(String songsDirectory) {
        File folder = new File(songsDirectory);
        File[] listOfFiles = folder.listFiles();

        int i = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
                generateSongFromFile(file);
            }
//
//            if(i == 1){
//                return;
//            }
//            i++;
        }
    }

    private void generateSongFromFile(File file) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), "UTF8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            generateSongFromString(bufferedReader, file.getName().substring(0,file.getName().length()-4));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private void generateSongFromString(BufferedReader bufferedReader, String songTitle) throws IOException {
        System.out.println(songTitle);
        Song song = new Song(songTitle, "", new Date());
        songRepository.save(song);
        String lyrics = "";

        boolean newLineIsLinkName = false;
        String line = "";
        String nextLinkUrl = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("iframe")) {
                YTData ytData = processYtData(line, song);
                ytDataRepository.save(ytData);
            } else if (line.contains("http")) {
                nextLinkUrl = line;
                newLineIsLinkName = true;
            } else {
                if (newLineIsLinkName) {
                    Link link = new Link(line, nextLinkUrl, song);
                    linkRepository.save(link);
                    newLineIsLinkName = false;
                } else {
                    lyrics += (line + "\n");
                }
            }
        }
        song.setLyrics(lyrics);
        songRepository.save(song);
    }

    private YTData processYtData(String line, Song song) {
        Pattern pattern = Pattern.compile(".*youtube\\.com/embed/([^\"]+).*");
        Matcher matcher = pattern.matcher(line);
        String group1 = "";
        while (matcher.find()) {
           group1 = matcher.group(1);
        }
        try {
//            group = matcher.group(1);
            YTData ytDataA = new YTData(group1, song);
            return ytDataA;
        } catch (Exception e) {
            int i = 5;
            return null;
        }
    }
}
