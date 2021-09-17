package com.def.module;

import com.def.entity.Hero;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HTMLParser {
    public static void parseHTML(URL url) {

        Document document;
        try {

            document = Jsoup.connect(String.valueOf(url)).get();

            Elements blocks = document.select("div.card-body is-sliding");
            Elements names = blocks.select("p.card-body__headline");
            Elements figure = document.select("figure.img__wrapper ");
            Elements img = figure.select("img[mvl-type=explore]");
            String src = img.attr("src");
            System.out.println(src);

            URLConnection openConnection = null;

           for (int i = 0; i < img.size(); i++){
               openConnection = new URL(img.attr("src")).openConnection();
           }
            assert openConnection != null;
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            String namesLine = names.text();
            String[] toSplit = namesLine.split(" ");
            List<Hero> personList = new ArrayList<>();

            BufferedImage image = ImageIO.read(openConnection.getInputStream());

            File file = null;
            for (String s : toSplit) {
                personList.add(new Hero(s));
                file = new File("/home/images/" + s);
            }
            if (file != null){
                file.createNewFile();
                ImageIO.write(image, "jpg", file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
