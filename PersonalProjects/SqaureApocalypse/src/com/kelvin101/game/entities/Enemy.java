package com.kelvin101.game.entities;

public abstract class Enemy extends Entity
{
    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 5.0f;
    public static final int DEFAULT_WIDTH = 32,
                            DEFAULT_HEIGHT = 32;

    protected int health;
    protected float speed;
    protected float velX, velY;
    protected int damage;



    public Enemy(float x, float y, int width, int height)
    {
        super(x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        velX = 0;
        velY = 0;

    }

    public void move()
    {
        x += velX;
        y += velY;
    }

    //GETTERS AND SETTERS

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public int getDamage()
    {
        return damage;
    }
}
