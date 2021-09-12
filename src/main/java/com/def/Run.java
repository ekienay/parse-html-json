package com.def;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class Run {
    public static void main(String[] args) throws MalformedURLException {
        String result = parseJSON(new URL("http://worldtimeapi.org/api/timezone/America/Santiago"));
        JSONObject obj = (JSONObject) JSONValue.parse(result);
        System.out.println("Date and time: " + obj.get("datetime"));
    }

    public static String parseJSON(URL url){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()))){
            String line;
            while ((line = bf.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
