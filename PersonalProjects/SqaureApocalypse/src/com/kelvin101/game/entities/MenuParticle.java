package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;

import java.awt.*;
import java.util.Random;

import static com.kelvin101.game.states.MenuState.getMenuButtons;

public class MenuParticle extends Entity
{
    protected float velX, velY;
    private Color[] colors = {
                    new Color(112, 128, 144),
                    new Color(230, 230, 250),
                    new Color(255, 228, 225),
                    new Color(255, 218, 185),
                    new Color(205, 133, 63),
                    new Color(255, 182, 193),
                    new Color(72, 61, 139),
                    new Color(70, 130, 180),
                    new Color(0, 128, 128),
                    new Color(60, 179, 113),
                    new Color(240, 230, 140),
                    new Color(255, 99, 71),
                    new Color(255, 140, 0),
                    new Color(250, 128, 114),
                    };
    private Color c;
    private Game game;
    private Handler menuHandler;
    private ID id;
    Random r = new Random();

    public MenuParticle(Game game, Handler handler, float x, float y)
    {
        super(x, y, 32, 32);
        this.game = game;
        this.menuHandler = handler;
        id = ID.MenuParticle;

        int rand = new Random().nextInt(colors.length);

        velX = 3 + r.nextInt(5);
        velY = 3 + r.nextInt(5);

        c = colors[rand];

    }


    public void tick()
    {
        x += velX;
        y += velY;

        if (y <= 0 || y >= game.getHeight() - 32)
        {
            if (velY < 0)
            {
                velY = -(r.nextInt(7) + 1) * -1;
            }
            else
            {
                velY = (r.nextInt(7) + 1) * -1;
            }
        }

        if (x <= 0 || x >= game.getWidth() - 16)
        {
            if (velX < 0)
            {
                velX = -(r.nextInt(7) + 1) * -1;
            }
            else
            {
                velX = (r.nextInt(7) + 1) * -1;
            }
        }

        for (int i = 0; i < getMenuButtons().size(); i++)
        {
            if (getMenuButtons().get(i).getBounds().intersects(getBounds()))
            {
                velX *= -1;
                velY *= -1;
            }
        }
    }


    public void render(Graphics2D g)
    {
        g.setColor(c);
        g.setComposite(makeTransparent(1f));
        g.fillRect((int)x, (int)y, 32, 32);
    }

    private AlphaComposite makeTransparent(float a)
    {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, a));
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)(y), 32, 32);
    }

    @Override
    public int getDamage() {
        return 0;
    }

    public ID getID()
    {
        return id;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int h) {

    }
}
