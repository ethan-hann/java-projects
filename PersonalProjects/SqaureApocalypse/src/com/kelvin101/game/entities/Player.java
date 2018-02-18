package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.gfx.CharTrail;
import com.kelvin101.game.input.KeyManager;
import com.kelvin101.game.states.MenuState;
import com.kelvin101.game.states.State;

import java.awt.*;

public class Player extends Enemy
{
    private Game game;
    private int level = 1;
    private int score = 0;
    public static float speed = 5;
    public static int health = 100;
    public static Color charColor = Colors.WHITE;
    private Handler handler;
    private ID id;
    private KeyManager k;
    public Player(Game game, Handler handler, float x, float y)
    {
        super(x, y, Enemy.DEFAULT_WIDTH, Enemy.DEFAULT_HEIGHT);
        this.game = game;
        this.handler = handler;
        id = ID.Player;
        k = game.getKeyManager();
    }


    public void tick()
    {
        speed = Game.clamp(speed, 5, 20);
        x = Game.clamp(x, 2, game.getWidth() - 34);
        y = Game.clamp(y, 2, game.getHeight() - 42);

        getInput();
        Player.health = Player.health - collision(handler);
        move();
        handler.add(new CharTrail((int)x, (int)y, 40, 40, 0.08, Player.charColor, handler));
    }

    private void getInput()
    {
        velX = 0;
        velY = 0;

        if (State.getState() == game.gameState)
        {
            if (k.up)
            {
                velY = -speed;
            }
            if (k.down)
            {
                velY = speed;
            }
            if (k.left)
            {
                velX = -speed;
            }
            if (k.right)
            {
                velX = speed;
            }
            if (k.space)
            {
                k.space = false;
                State.setState(game.shopState);
            }
            if (k.p)
            {
                k.space = false;
                State.setState(game.pausedState);
            }
            if (k.esc)
            {
                k.space = false;
                game.menuState = new MenuState(this.game);
                State.setState(game.menuState);
            }
        }

        if (State.getState() == game.gameState && level == 50)
        {
            if (k.num5)
            {
                handler.add(new PlayerBullet(x, y, 16, 16, handler, game));
            }
        }
    }

    public void render(Graphics2D g)
    {
        g.setColor(charColor);
        g.fillOval((int)x, (int)y, 40, 40);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 36, 36);
    }

    private int collision(Handler handler)
    {
        if (handler.size() > 0)
        {
            for (int i = 0; i < handler.size(); i++)
            {
                Entity e = handler.get(i);
                if (e.getID().toString().contains("BasicEnemy"))
                {
                    if (getBounds().intersects(e.getBounds()))
                    {
                        System.out.println("Basic");
                        return 1;
                    }
                }
                if (e.getID().toString().contains("FastEnemy"))
                {
                    if (getBounds().intersects(e.getBounds()))
                    {
                        System.out.println("Fast");
                        return 2;
                    }
                }
                if (e.getID().toString().contains("SmartEnemy"))
                {
                    if (getBounds().intersects(e.getBounds()))
                    {
                        System.out.println("Smart");
                        return 3;
                    }
                }
                if (e.getID().toString().contains("HardEnemy"))
                {
                    if (getBounds().intersects(e.getBounds()))
                    {
                        System.out.println("Hard");
                        return 4;
                    }
                }
                if (e.getID().toString().contains("BossEnemy"))
                {
                    if (getBounds().intersects(e.getBounds()))
                    {
                        return 1000;
                    }
                }
                if (e.getID().toString().contains("BossBullet"))
                {
                    if (getBounds().intersects(e.getBounds()))
                    {
                        return 2;
                    }
                }
            }
        }
        return 0;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int s)
    {
        score = s;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int l)
    {
        level = l;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public ID getID()
    {
        return id;
    }
}
