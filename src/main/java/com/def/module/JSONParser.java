package com.def.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JSONParser {

    public static String parseJSON(URL url) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
