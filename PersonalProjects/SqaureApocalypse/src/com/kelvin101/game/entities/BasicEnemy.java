package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.gfx.Trail;

import java.awt.*;

public class BasicEnemy extends Enemy
{
    private Handler handler;
    private Game game;
    private final int damage = 1;
    private ID id;

    public BasicEnemy(float x, float y, int width, int height, Handler handler, Game game)
    {
        super(x, y, width, height);
        this.handler = handler;
        this.game = game;
        id = ID.BasicEnemy;
        velX = 4;
        velY = 4;
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        if (y <= 0 || y >= game.getHeight() - 32) velY *= -1;
        if (x <= 0 || x >= game.getWidth() - 16) velX *= -1;

        handler.add(new Trail((int)x, (int)y, 32, 32, 0.08, Colors.RED, handler));

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Colors.RED);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public int getDamage()
    {
        return damage;
    }

    public ID getID()
    {
        return id;
    }
}
