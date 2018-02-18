package com.kelvin101.game.entities;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.ID;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.gfx.Trail;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends Enemy
{
    private Handler handler;
    private Game game;
    private Random r = new Random();
    public static int health = 1000;
    private int timer1 = 20;
    private int timer2 = 50;
    private ID id;
    public BossEnemy(float x, float y, int width, int height, Handler handler, Game game)
    {
        super(x, y, width, height);
        this.handler = handler;
        this.game = game;
        id = ID.BossEnemy;

        velX = 0;
        velY = 3;
    }

    @Override
    public void tick()
    {
        if (health > 0)
        {
            x += velX;
            y += velY;

            if (timer1 <= 0)
            {
                velY = 0;
            }
            else
            {
                timer1--;
            }
            if (timer1 <= 0)
            {
                timer2--;
            }
            if (timer2 <= 0)
            {
                if (velX == 0) {
                    velX = 6;
                }

                if (velX > 0) {
                    velX += 0.05;
                } else if (velX < 0) {
                    velX -= 0.05;
                }
                velX = Game.clamp(velX, -10, 10);

                int bulletSpawn = r.nextInt(7);
                if (bulletSpawn == 0)
                {
                    handler.add(new BossBullet((int)x + 48, (int)y + 48, 16, 16, handler, game));
                }

                if (x <= 0 || x >= game.getWidth() - 96) velX *= -1;

                handler.add(new Trail((int)x, (int)y, 64, 64, 0.06, Colors.KHAKI, handler));
            }

        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Colors.KHAKI);
        g.fillRect((int)x, (int)y, 64, 64);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 64, 64);
    }

    @Override
    public ID getID()
    {
        return id;
    }
}
