package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;

import java.awt.*;

public class PlayerBullet extends Enemy
{
    private Handler handler;
    private Game game;
    private Entity e;
    private Entity parent;
    private Entity boss;

    private int damage = 1;
    private ID id;

    public PlayerBullet(float x, float y, int width, int height, Handler handler, Game game)
    {
        super(x, y, width, height);
        this.handler = handler;
        this.game = game;
        id = ID.PlayerBullet;

        velX = 0;
        velY = -8;
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        for (int i = 0; i < handler.size(); i++)
        {
            e = handler.get(i);
            if (e.getID() == ID.Player)
            {
                parent = e;
            }
            if (e.getID() == ID.BossEnemy)
            {
                boss = e;
            }
        }

        if (y >= parent.getY())
        {
            handler.remove(this);
            //y = parent.getY() + 48;
        }

        if (y >= game.getHeight() || x >= game.getWidth())
        {
            handler.remove(this);
        }

        if (getBounds().intersects(boss.getBounds()))
        {
            BossEnemy.health = BossEnemy.health - damage;
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Player.charColor);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public ID getID()
    {
        return id;
    }

    @Override
    public int getHealth()
    {
        return 0;
    }
}
