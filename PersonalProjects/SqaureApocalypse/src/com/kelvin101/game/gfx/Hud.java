package com.kelvin101.game.gfx;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.entities.BossEnemy;
import com.kelvin101.game.entities.Player;
import com.kelvin101.game.states.GameState;

import java.awt.*;

public class Hud
{
    private Handler handler;
    private Player player;
    private Game game;
    public static float bounds = 0;
    private float greenValue = 255;

    public Hud(Game game, Handler handler, Player player)
    {
        this.game = game;
        this.handler = handler;
        this.player = player;
    }

    public void tick()
    {
        Player.health = (int) Game.clamp(Player.health, 0, 100 + (bounds / 2));
        bounds = Game.clamp(bounds, 0, 700);
        greenValue = Player.health * 2;
        greenValue = Game.clamp(greenValue, 0, 255);
        player.setScore(player.getScore() + 1);
    }

    public void render(Graphics2D g)
    {
        Font jet = new Font("Jet Set", Font.PLAIN, 22);
        Font jetItalic = new Font("Jet Set", Font.ITALIC, 30);
        g.setFont(jet);
        g.setColor(Colors.DARK_GREY);
        g.fillRect(15, 15, 200 + (int)bounds, 32);

        g.setColor(new Color(75, (int)greenValue, 0));
        g.fillRect(15, 15, Player.health * 2, 32);

        g.setColor(Colors.GREEN);
        g.drawRect(15, 15, 200 + (int)bounds, 32);

        g.setColor(Colors.SALMON);
        g.drawString("Score: " + player.getScore(), 15, 75);
        g.drawString("Level: " + player.getLevel(), 15, 100);
        g.drawString("Current Speed: " + Player.speed + "px/s", 15, 128);
        g.drawString("<SPACE> - Shop", 15, 156);
        g.drawString("<P> - Pause", 15, 184);
        g.drawString("<ESC> - Main Menu", 15, 212);

        if (player.getLevel() == 50)
        {
            g.drawString("Boss HP: " + BossEnemy.health, 15, 240);
        }
    }
}
