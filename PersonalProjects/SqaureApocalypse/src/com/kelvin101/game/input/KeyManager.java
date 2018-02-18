package com.kelvin101.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    private boolean[] keys;
    public boolean up, down, left, right, space, p, esc, num5;

    public KeyManager()
    {
        keys = new boolean[256];
    }

    public boolean isKeyPressed(KeyEvent e)
    {
        return keys[e.getKeyCode()];
    }

    public void tick()
    {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE];
        p = keys[KeyEvent.VK_P];
        esc = keys[KeyEvent.VK_ESCAPE];
        num5 = keys[KeyEvent.VK_NUMPAD5];
    }

    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e)
    {

    }
}
