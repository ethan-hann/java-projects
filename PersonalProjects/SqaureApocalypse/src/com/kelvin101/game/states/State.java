package com.kelvin101.game.states;

import com.kelvin101.game.Game;

import java.awt.*;

public abstract class State
{
    protected Game game;
    private static State currentState = null;

    public State(Game game)
    {
        this.game = game;
    }

    public static void setState(State state)
    {
        currentState = state;
    }

    public static State getState()
    {
        return currentState;
    }

    public boolean isMouseClicked(double mX, double mY, double x, double y, double width, double height)
    {
        if (mX > x && mX < x + width)
        {
            if (mY > y && mY < y + height)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    //CLASS


    public abstract void tick();
    public abstract void render(Graphics2D g);
}
