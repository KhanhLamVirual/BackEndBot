package com.bot.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import arc.util.Log;
import mindustry.Vars;
import mindustry.maps.Maps;


/**
 * Hello world!
 *
 */

@RestController
@EnableAutoConfiguration
public class App 
{

    @PostMapping("/ping")
    public Map<String, Object> ping(@RequestBody Map<String, Object> jsonpost)
    {
        Map<String, Object> data = new HashMap<>();
        String host = (String)jsonpost.get("host");
        int port = (int)jsonpost.get("port");
        ServerHandler.pingHost(host, port, (h) -> {
            data.put("name", h.name);
            data.put("desc", h.description);
            data.put("map", h.mapname);
            data.put("version", h.version);
            data.put("vertype", h.versionType);
            data.put("mode", h.modeName);
            data.put("wave", h.wave);
            data.put("players", h.players);
            data.put("maxPlayer", h.playerLimit);
            data.put("ping", h.ping);
        }, (e) -> {
            data.put("error", "serveroffline");
        });
        return data;
    }

    public static void main( String[] args )
    {   
        SpringApplication.run(App.class, args);
    }
}
