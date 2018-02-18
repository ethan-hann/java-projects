package com.kelvin101.game.input;

import net.java.games.input.Mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
    public static boolean leftPressed, rightPressed;
    private int mX, mY;

    public MouseManager()
    {

    }

    public boolean isLeftPressed()
    {
        return leftPressed;
    }

    public boolean isRightPressed()
    {
        return rightPressed;
    }

    public int getMouseX()
    {
        return mX;
    }

    public int getMouseY()
    {
        return mY;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftPressed = true;
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftPressed = false;
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mX = e.getX();
        mY = e.getY();
    }

}