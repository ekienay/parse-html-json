package com.def;

import com.def.module.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.MalformedURLException;
import java.net.URL;


public class Run {
    public static void main(String[] args) throws MalformedURLException {
        String result = JSONParser.parseJSON(new URL("http://worldtimeapi.org/api/timezone/America/Santiago"));
        JSONObject obj = (JSONObject) JSONValue.parse(result);
        System.out.println("Date and time: " + obj.get("datetime"));
    }
}
