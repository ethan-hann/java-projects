package com.kelvin101.game.gfx;

import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.entities.Entity;

import java.awt.*;

public class CharTrail extends Entity
{
    private float alpha = 1;
    private Handler handler;
    private Color c;
    private int width, height;
    private double life;
    private ID id;

    public CharTrail(int x, int y, int width, int height, double life, Color c, Handler handler)
    {
        super(x, y, width, height);
        this.handler = handler;
        this.c = c;
        this.width = width;
        this.height = height;
        this.life = life;
        id = ID.CharTrail;
    }

    public void tick()
    {
        if (alpha > life)
        {
            alpha -= (life - 0.001);
        }
        else
        {
            handler.remove(this);
        }
    }

    public void render(Graphics2D g)
    {
        g.setComposite(makeTransparent(alpha));
        g.setColor(c);
        g.fillOval((int)x, (int)y, width, height);
        g.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha)
    {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    public Rectangle getBounds()
    {
        return null;
    }

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
