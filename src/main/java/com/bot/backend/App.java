package com.bot.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import arc.util.Log;
import mindustry.maps.Maps;

import com.bot.backend.components.ContentHandler;
import com.bot.backend.components.ResourceUtils;

import static com.bot.backend.Vars.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */

@RestController
@EnableAutoConfiguration
public class App 
{

    @PostMapping("/map")
    public Object map(@RequestBody String path)
    {

        try {
            Map<String, Object> json = new HashMap<>();
            mindustry.maps.Map map = ContentHandler.parseMap(new File(path.replace("%2F", "/").replace("=", "")));
            byte[] image = ContentHandler.parseMapImage(map);

            
            
            json.put("name", map.name());
            json.put("description", map.description());
            json.put("author", map.author());
            json.put("range", map.height + "x" + map.width);
            json.put("image", image);

            return json;

        } catch (Exception e)  {
            e.printStackTrace();
            return null;
        }
    }

    public static void main( String[] args )
    {   
        cache.delete();

        dataDirectory.mkdirs();
        cache.mkdirs();
        resources.mkdirs();
        sprites.mkdirs();

        ResourceUtils.init();
        SpringApplication.run(App.class, args);
    }
}
