package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.gfx.Trail;

import java.awt.*;
import java.util.Random;

public class HardEnemy extends Enemy
{
    private ID id;
    private Random r = new Random();
    private Handler handler;
    private Game game;

    public HardEnemy(float x, float y, int width, int height, Handler handler, Game game)
    {
        super(x, y, width, height);
        this.handler = handler;
        this.game = game;
        id = ID.HardEnemy;

        velX = 5;
        velY = 5;
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        if (y <= 0 || y >= game.getHeight() - 32)
        {
            if (velY < 0)
            {
                velY = -(r.nextInt(15) + 1) * -1;
            }
            else
            {
                velY = (r.nextInt(15) + 1) * -1;
            }
        }

        if (x <= 0 || x >= game.getWidth() - 32)
        {
            if (velX < 0)
            {
                velX = -(r.nextInt(15) + 1) * -1;
            }
            else
            {
                velX = (r.nextInt(15) + 1) * -1;
            }
        }

        handler.add(new Trail((int)x, (int)y, 32, 32, 0.08, Colors.TOMATO, handler));

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Colors.TOMATO);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public ID getID()
    {
        return id;
    }
}
