package com.kelvin101.game.entities;

import com.kelvin101.game.ID;

import java.awt.*;

public abstract class Entity
{
    protected float x, y;
    protected int width, height;
    protected int health;

    public Entity(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics2D g);
    public abstract Rectangle getBounds();
    public abstract int getDamage();
    public abstract ID getID();
    public abstract int getHealth();
    public abstract void setHealth(int h);
}

