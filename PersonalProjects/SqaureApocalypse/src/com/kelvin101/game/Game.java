package com.kelvin101.game;

import com.kelvin101.game.audio.Audio;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.display.Display;
import com.kelvin101.game.gfx.Assets;
import com.kelvin101.game.input.KeyManager;
import com.kelvin101.game.input.MouseManager;
import com.kelvin101.game.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
    public static Display display;
    private int width, height;
    public String title;
    public static final String MUSIC_RESOURCE_LOCATION = "./res/music/";
    public static final String FONT_RESOURCE_LOCATION = "./res/fonts/";

    private boolean running = false;

    //Declaring main objects
    private Thread thread;
    private BufferStrategy bs;
    private Graphics2D g;

    //Declaring an audio player object
    public static final Audio audio = new Audio();

    //States
    public State gameState;
    public State menuState;
    public State helpState;
    public State shopState;
    public State pausedState;
    public State gameOverState;
    public State charCustomizeState;
    public State gameWin;

    //Inputs
    private KeyManager keyManager;
    private MouseManager mouseManager;

    public Game(String title, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init()
    {
        //Initializing display
        display = new Display(title, width, height); //create a new window for the game
        display.getFrame().addKeyListener(keyManager); //add the key listener to game for keyboard input
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);

        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);


        audio.load(); //Load audio into game
        Assets.init(); //Initialize assets (fonts, etc...)

        //Initializing States
        menuState = new MenuState(this);
        helpState = new HelpState(this);
        pausedState = new PausedState(this);

        //Setting game state
        State.setState(menuState);
    }

    private void tick()
    {
        keyManager.tick();
        if (State.getState() != null)
        {
            State.getState().tick();
        }
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = (Graphics2D) bs.getDrawGraphics();

        //Clear screen
        g.clearRect(0, 0, width, height);

        //Begin drawing to screen
        g.setColor(Colors.DARK_GREY);
        g.fillRect(0, 0, width, height);

        if (State.getState() != null)
        {
            State.getState().render(g);
        }

        //End drawing
        bs.show();
        g.dispose();
    }

    public void run()
    {
        init();

        int fps = 60;
        double timePerTick = 1e9 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1e9)
            {
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public MouseManager getMouseManager()
    {
        return mouseManager;
    }

    public synchronized void start()
    {
        if (running)
        {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if (!running)
        {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWidth()
    {
        return width;
    }


    public int getHeight()
    {
        return height;
    }

    public static float clamp(float var, float min, float max)
    {
        if (var >= max)
        {
            return var = max;
        }
        else if (var <= min)
        {
            return var = min;
        }
        else
        {
            return var;
        }
    }
}
