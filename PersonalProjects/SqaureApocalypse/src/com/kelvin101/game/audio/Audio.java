package com.kelvin101.game.audio;

import com.kelvin101.game.Game;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;

public class Audio {

    private static Map<String, Music> music = new HashMap<>();

    public void load()
    {
        try
        {
            music.put("background", new Music(Game.MUSIC_RESOURCE_LOCATION + "background.wav"));
            music.put("menu", new Music(Game.MUSIC_RESOURCE_LOCATION + "menu.ogg"));
            music.put("boss", new Music(Game.MUSIC_RESOURCE_LOCATION + "boss.wav"));
            music.put("victory", new Music(Game.MUSIC_RESOURCE_LOCATION + "victory.wav"));
            music.put("lose", new Music(Game.MUSIC_RESOURCE_LOCATION + "lose.wav"));
        }catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    public Music playMusic(String key)
    {
        return music.get(key);
    }

    public void stopMusic(String key)
    {
        music.get(key).stop();
    }
}
