package com.def.module;

import com.def.entity.Hero;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HTMLParser {
    public static void parseHTML(URL url) {

        Document document;
        try {
            UUID uuid = UUID.randomUUID();

            document = Jsoup.connect(String.valueOf(url)).get();

            Elements blocks = document.select("div.card-body is-sliding");
            Elements names = blocks.select("p.card-body__headline");
            Elements figure = document.select("figure.img__wrapper ");
            Elements img = figure.select("img[mvl-type=explore]");

            String namesLine = names.text();
            String[] toSplit = namesLine.split(" ");
            List<Hero> personList = new ArrayList<>();

            File file;

            for (String s : toSplit) {
                personList.add(new Hero(s));
            }

            URLConnection openConnection;
            BufferedImage image;
            while (img.size() != 0){
                for (Element element : img){
                    openConnection = new URL(element.attr("src")).openConnection();
                    image = ImageIO.read(openConnection.getInputStream());
                    file = new File("/home/sixtify/images/" + uuid + ".jpg");
                    ImageIO.write(image, "jpg", file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
