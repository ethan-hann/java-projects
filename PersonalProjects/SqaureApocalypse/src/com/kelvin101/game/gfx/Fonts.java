package com.kelvin101.game.gfx;

import com.kelvin101.game.Game;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class Fonts {
    private static ArrayList<Fonts> fontList = new ArrayList<>();
    private static String fontPath;

    public Fonts(String filePath)
    {
        Fonts.fontPath = Game.FONT_RESOURCE_LOCATION + filePath;
        registerFont();
    }

    private void registerFont()
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try
        {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addFont(Fonts font)
    {
        fontList.add(font);
    }
}