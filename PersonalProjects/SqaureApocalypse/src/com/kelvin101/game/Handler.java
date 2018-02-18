package com.kelvin101.game;

import com.kelvin101.game.entities.Entity;

import java.awt.*;
import java.util.ArrayList;

public class Handler
{
    public ArrayList<Entity> entities = new ArrayList<>();
    private Game game;

    public Handler(Game game)
    {
        this.game = game;
    }

    public void tick()
    {
        for (int i = 0; i < entities.size(); i++)
        {
            Entity e = entities.get(i);
            e.tick();
        }
    }

    public void render(Graphics2D g)
    {
        for (int i = 0; i < entities.size(); i++)
        {
            Entity e = entities.get(i);
            e.render(g);
        }
    }

    public void add(Entity e)
    {
        entities.add(e);
    }

    public void remove(Entity e)
    {
        for (int i = 0; i < entities.size(); i++)
        {
            Entity temp = entities.get(i);
            if (temp == e)
            {
                entities.remove(i);
            }
        }
    }

    public int size()
    {
        return entities.size();
    }

    public Entity get(int index)
    {
        return entities.get(index);
    }

    public void clear()
    {
        entities.clear();
    }

    //GETTERS AND SETTERS

}
