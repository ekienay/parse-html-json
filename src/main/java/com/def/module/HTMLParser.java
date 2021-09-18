package com.def.module;

import com.def.entity.Hero;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HTMLParser {
    public  static void parseHTML(URL url) {
        Document document;
        try {

            document = Jsoup.connect(String.valueOf(url)).get();

            Elements contentGrid = document.select("div[class=content-grid content-grid--light]");
            Elements grid = contentGrid.select("div[class=grid-base grid__6]");
            Elements heroesCards = grid.select("div[class=mvl-card mvl-card--explore]");
            Elements avatars = grid.select("img[mvl-type=explore]");

            List<Hero> personList = new ArrayList<>();

            var ref = new Object() {
                File file;
            };

            URLConnection openConnection;
            BufferedImage image;
            Hero hero;

            for (Element name : heroesCards){
                name.select("p[class=card-body__headline]");
                hero = new Hero();
                hero.setName(name.text());
                personList.add(hero);
            }

            for (Element avatar : avatars){
                openConnection = new URL(avatar.attr("src")).openConnection();
                ref.file = new File("/home/sixtify/images/" + UUID.randomUUID() + ".jpg");
                image = ImageIO.read(openConnection.getInputStream());
                ImageIO.write(image, "jpg", ref.file);
                personList.forEach(h -> h.setAvatarImageName(ref.file.getName()));
            }

            personList.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
