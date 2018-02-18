package com.kelvin101.game.states;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.entities.BossEnemy;
import com.kelvin101.game.entities.MenuParticle;
import com.kelvin101.game.entities.Player;
import com.kelvin101.game.gfx.Hud;
import com.kelvin101.game.input.MouseManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static com.kelvin101.game.Game.audio;

public class MenuState extends State
{
    public static Handler menuHandler;
    private Handler handler;
    private static ArrayList<Rectangle> menuButtons;
    private Random r = new Random();
    public MenuState(Game game)
    {
        super(game);
        audio.stopMusic("background");
        audio.playMusic("menu").loop();
        menuHandler = new Handler(game);
        handler = new Handler(game);
        menuButtons = new ArrayList<>();
        for (int i = 0; i < 35; i++)
        {
            menuHandler.add(new MenuParticle(game, menuHandler, 10 + r.nextInt(1600), 1 + 10 + r.nextInt(144)));
        }
    }

    public void tick()
    {
        float x = game.getMouseManager().getMouseX();
        float y = game.getMouseManager().getMouseY();
        if (game.getMouseManager().isLeftPressed())
        {
            //Play Game
            if (isMouseClicked(x, y, 580, 340, 400, 64))
            {
                MouseManager.leftPressed = false;
                game.gameState = new GameState(game, handler, new Player(game, handler, game.getWidth() / 2, game.getHeight() - 64));
                Player.speed = 5;
                Player.health = 100;
                BossEnemy.health = 1000;
                Hud.bounds = 0;
                State.setState(game.gameState);
            }

            //Customize Character
            if (isMouseClicked(x, y, 580, 468, 400, 64))
            {
                MouseManager.leftPressed = false;
                game.charCustomizeState = new CharacterCustomizeState(game);
                State.setState(game.charCustomizeState);
            }

            //Help
            if (isMouseClicked(x, y, 580, 596, 400, 64))
            {
                MouseManager.leftPressed = false;
                State.setState(game.helpState);
            }

            //Quit
            if (isMouseClicked(x, y, 580, 724, 400, 64))
            {
                System.exit(0);
            }
        }
        if (menuHandler.size() < 20)
        {
            menuHandler.add(new MenuParticle(game, menuHandler, 10 + r.nextInt(1600), 1 + 10 + r.nextInt(144)));
        }
        menuHandler.tick();
    }


    public void render(Graphics2D g)
    {
        menuHandler.render(g);

        Font jetItalic = new Font("Jet Set", Font.ITALIC, 30);
        Font zBold = new Font("Zorque", Font.BOLD, 80);
        g.setFont(zBold);

        g.setColor(Colors.BLACK);
        g.drawString("Square Apocalypse", (game.getWidth() / 2) - 455, 297);

        g.setColor(Colors.LIGHT_RED);
        g.drawString("Square Apocalypse", (game.getWidth() / 2) - 450, 292);

        g.setColor(Colors.RED);
        g.drawString("Square Apocalypse", (game.getWidth() / 2) - 445, 287);

        g.setFont(jetItalic);
        g.setColor(Colors.SALMON);
        g.drawString("Play Game", 705, 385);
        g.drawString("Customize Character", 620, 510);
        g.drawString("Help", 744, 638);
        g.drawString("Quit", 744, 766);

        menuButtons.add(0, new Rectangle(580, 340, 400, 64));
        menuButtons.add(1, new Rectangle(580, 468, 400, 64));
        menuButtons.add(2, new Rectangle(580, 596, 400, 64));
        menuButtons.add(3, new Rectangle(580, 724, 400, 64));


        g.setStroke(new BasicStroke(6.2f));
        g.setColor(Colors.MED_SEA_GREEN);
        g.drawRoundRect(580, 340, 400, 64, 32, 32); //play rect
        g.drawRoundRect(580, 468, 400, 64, 32, 32); //character rect
        g.drawRoundRect(580, 596, 400, 64, 32, 32); //help rect
        g.drawRoundRect(580, 724, 400, 64, 32, 32); //quit rect
    }

    public static ArrayList<Rectangle> getMenuButtons()
    {
        return menuButtons;
    }
}
