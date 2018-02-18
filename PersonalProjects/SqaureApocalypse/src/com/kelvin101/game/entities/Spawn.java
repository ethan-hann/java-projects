package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.audio.Audio;

import java.awt.*;
import java.util.Random;

public class Spawn
{
    private Handler handler;
    private Player player;
    private Game game;
    private Random r = new Random();
    private int scoreKeep;
    //private Clock clock;

    public Spawn(Game game, Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        this.game = game;
    }

    public void tick()
    {
        scoreKeep++;
        if(scoreKeep >= 500 && player.getLevel() != 50)
        {
            scoreKeep = 0;
            player.setLevel(player.getLevel() + 1);
            if (player.getLevel() == 2)
            {
                handler.add(new BasicEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
            }
            if (player.getLevel() == 3)
            {
                handler.add(new BasicEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
            }
            if (player.getLevel() == 4)
            {
                handler.add(new BasicEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
            }
            if (player.getLevel() == 5)
            {
                handler.add(new FastEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
            }
            if (player.getLevel() == 7)
            {
                handler.add(new FastEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
            }
            if (player.getLevel() == 9)
            {
                handler.add(new FastEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
            }
            if (player.getLevel() == 11)
            {
                handler.add(new SmartEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()) + 25, 32, 32, handler, game, player));
            }
            if (player.getLevel() % 5 == 0 && player.getLevel() != 50 && player.getLevel() != 45)
            {
                handler.add(new BasicEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
                handler.add(new HardEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()) + 25, 32, 32, handler, game));
            }
            if (player.getLevel() == 47)
            {
                handler.add(new BasicEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
                handler.add(new FastEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
                handler.add(new SmartEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()) + 25, 32, 32, handler, game, player));
            }
            if (player.getLevel() == 48)
            {
                handler.add(new SmartEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()) + 25, 32, 32, handler, game, player));
            }
            if (player.getLevel() == 49)
            {
                if (handler.entities.contains(player))
                {
                    Entity e = player;
                    handler.clear();
                    handler.add(e);
                }
                Game.audio.stopMusic("background");
                Game.audio.playMusic("boss").loop();
            }
            if (player.getLevel() == 50)
            {
                handler.add(new BossEnemy((game.getWidth() / 2) - 48, -105, 64, 64, handler, game));
            }
        }
    }
}
