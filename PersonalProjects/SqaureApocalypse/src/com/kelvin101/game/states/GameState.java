package com.kelvin101.game.states;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.entities.*;
import com.kelvin101.game.gfx.Hud;
import com.kelvin101.game.input.KeyManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static com.kelvin101.game.Game.audio;

public class GameState extends State
{
    private Handler handler;
    private Game game;
    private Player player;
    private Spawn spawn;
    private Hud hud;
    private KeyManager k;
    private Random r = new Random();
    private Entity e;
    private Entity boss;

    public GameState(Game game, Handler handler, Player player)
    {
        super(game);
        audio.stopMusic("menu");
        audio.playMusic("background").loop();
        this.game = game;
        this.handler = handler;
        this.player = player;
        game.shopState = new ShopState(this.game, this.player);
        game.pausedState = new PausedState(this.game);
        hud = new Hud(this.game, this.handler, this.player);
        spawn = new Spawn(this.game, this.handler, this.player);
        k = this.game.getKeyManager();
        handler.add(player);
        handler.add(new BasicEnemy(r.nextInt(game.getWidth()) + 25, r.nextInt(game.getHeight()), 32, 32, handler, game));
    }


    public void tick()
    {
        handler.tick();
        hud.tick();
        spawn.tick();
        if (Player.health <= 0)
        {
            if (player.getLevel() < 50)
            {
                Game.audio.stopMusic("background");
            }
            else
            {
                Game.audio.stopMusic("boss");
            }
            game.gameOverState = new GameOverState(this.game, player, handler);
            Game.audio.playMusic("lose");
            State.setState(game.gameOverState);
        }
        if (BossEnemy.health <= 0)
        {
            Game.audio.stopMusic("boss");
            game.gameWin = new GameWinState(this.game, player, handler);
            Game.audio.playMusic("victory");
            State.setState(game.gameWin);
        }

        for (int i = 0; i < handler.size(); i++)
        {
            e = handler.get(i);
            if (e.getID() == ID.BossEnemy)
            {
                boss = e;
            }
        }

    }

    public void render(Graphics2D g)
    {
        handler.render(g);
        hud.render(g);
    }
}
