package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.display.Colors;

import java.awt.*;
import java.util.Random;

public class BossBullet extends Enemy
{
    private Handler handler;
    private Game game;
    private Random r = new Random();
    private int damage = 2;
    private ID id;
    private Entity parent;
    public BossBullet(float x, float y, int width, int height, Handler handler, Game game)
    {
        super(x, y, width, height);
        this.handler = handler;
        this.game = game;
        id = ID.BossBullet;

        velX = 0;
        velY = 8 + r.nextInt(16);
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        Entity e;
        for (int i = 0; i < handler.size(); i++)
        {
            e = handler.get(i);
            if (e.getID() == ID.BossEnemy)
            {
                parent = e;
            }
        }

        if (y <= parent.getY())
        {
            y = parent.getY() + 48;
        }

        if (y >= game.getHeight() || x >= game.getWidth())
        {
            handler.remove(this);
        }

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Colors.PERU);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    @Override
    public int getDamage()
    {
        return damage;
    }

    @Override
    public ID getID()
    {
        return id;
    }
}
