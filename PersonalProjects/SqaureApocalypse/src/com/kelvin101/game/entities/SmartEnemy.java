package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.gfx.Trail;

import java.awt.*;

public class SmartEnemy extends Enemy
{
    private Handler handler;
    private Game game;
    private Player player;
    private final int damage = 10;
    private ID id;

    public SmartEnemy(float x, float y, int width, int height, Handler handler, Game game, Player player)
    {
        super(x, y, width, height);
        this.game = game;
        this.handler = handler;
        this.player = player;
        id = ID.SmartEnemy;
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        float diffX = x - player.getX();
        float diffY = y - player.getY();
        double distance = Math.sqrt((Math.pow(player.getX() - x, 2)) + (Math.pow(player.getY() - y, 2)));

        velX = (float) ((-1.0 / distance) * diffX);
        velY = (float) ((-1.0 / distance) * diffY);


        if (y <= 0 || y >= game.getHeight() - 32) velY *= -1;
        if (x <= 0 || x >= game.getWidth() - 16) velX *= -1;

        handler.add(new Trail((int)x, (int)y, 32, 32, 0.08, Colors.DARK_SLATE_BLUE, handler));
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Colors.DARK_SLATE_BLUE);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle getBounds()
    {
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
