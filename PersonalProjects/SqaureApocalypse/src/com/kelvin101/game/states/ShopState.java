package com.kelvin101.game.states;

import com.kelvin101.game.Game;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.entities.Player;
import com.kelvin101.game.gfx.Hud;
import com.kelvin101.game.input.KeyManager;
import com.kelvin101.game.input.MouseManager;

import java.awt.*;

public class ShopState extends State
{
    private Player player;
    private KeyManager k;
    private int healthCost = 100;
    private int speedCost = 100;
    private int restoreCost = 100;
    private int healthCapicity = 10;
    private int speedCapacity = 1;

    public ShopState(Game game, Player player)
    {
        super(game);
        this.player = player;
        k = game.getKeyManager();
    }

    public void tick()
    {
        float x = game.getMouseManager().getMouseX();
        float y = game.getMouseManager().getMouseY();
        if (k.space)
        {
            k.space = false;
            State.setState(game.gameState);
        }
        if (game.getMouseManager().isLeftPressed())
        {
            //Upgrade health button
            if (isMouseClicked(x, y, 960, 228, 128, 96))
            {
                MouseManager.leftPressed = false;
                if (player.getScore() >= healthCost)
                {
                    player.setScore(player.getScore() - healthCost);
                    healthCost += 150;
                    healthCapicity += 10;
                    Hud.bounds += 20;
                    if (Player.health != 100)
                    {
                        Player.health = (int)((100 + (Hud.bounds / 2)) / 2);
                    }
                }
            }
            //Upgrade speed
            else if (isMouseClicked(x, y, 960, 384, 128, 96))
            {
                MouseManager.leftPressed = false;
                if (player.getScore() >= speedCost)
                {
                    if (player.getSpeed() >= 5 && player.getSpeed() < 20)
                    {
                        player.setScore(player.getScore() - speedCost);
                        speedCost += 150;
                        speedCapacity += 1;
                        Player.speed = Player.speed + 1;
                    }
                }
            }
            //Restore health
            else if (isMouseClicked(x, y, 960, 543, 128, 96))
            {
                MouseManager.leftPressed = false;
                if (player.getScore() >= restoreCost)
                {
                    player.setScore(player.getScore() - restoreCost);
                    restoreCost += 150;
                    Player.health = (int)(100 + (Hud.bounds / 2));
                }
            }
        }
    }

    public void render(Graphics2D g)
    {
        Font zBold = new Font("Zorque", Font.BOLD, 80);
        Font jetItalic = new Font("Jet Set", Font.ITALIC, 26);


        g.setFont(zBold);

        g.setColor(Colors.BLACK);
        g.drawString("Shop", (game.getWidth() / 2) - 138, 154);

        g.setColor(Colors.LIGHT_RED);
        g.drawString("Shop", (game.getWidth() / 2) - 133, 149);

        g.setColor(Colors.RED);
        g.drawString("Shop", (game.getWidth() / 2) - 128, 144);

        g.setFont(jetItalic);
        g.setStroke(new BasicStroke(6.2f));

        g.setColor(Colors.MED_SEA_GREEN);
        g.drawRoundRect(550, 228, 400, 96, 32, 32); //health upgrade
        if (player.getScore() >= healthCost)
        {
            g.drawRoundRect(960, 228, 144, 96, 32, 32);
        }
        else
        {
            g.setColor(Colors.RED);
            g.drawRoundRect(960, 228, 144, 96, 32, 32);
        }

        g.setColor(Colors.MED_SEA_GREEN);
        g.drawRoundRect(550, 384, 400, 96, 32, 32); //speed upgrade
        if (player.getScore() >= speedCost)
        {
            g.drawRoundRect(960, 384, 144, 96, 32, 32);
        }
        else
        {
            g.setColor(Colors.RED);
            g.drawRoundRect(960, 384, 144, 96, 32, 32);
        }

        g.setColor(Colors.MED_SEA_GREEN);
        g.drawRoundRect(550, 543, 400, 96, 32, 32); //full health restore
        if (player.getScore() >= restoreCost)
        {
            g.drawRoundRect(960, 543, 144, 96, 32, 32);
        }
        else
        {
            g.setColor(Colors.RED);
            g.drawRoundRect(960, 543, 144, 96, 32, 32);
        }

        g.setColor(Colors.SALMON);
        g.drawString("Upgrade Health Capacity", 570, 265);
        g.drawString(String.format("+ %d HP", healthCapicity), 570, 305);
        g.drawString(String.format("%d C's", healthCost), 975, 287);

        g.drawString("Upgrade Speed", 570, 420);
        g.drawString(String.format("+%d px/s", speedCapacity), 570, 460);
        g.drawString(String.format("%d C's", speedCost), 972, 445);

        g.drawString("Restore Health", 570, 580);
        g.drawString("+ Maximum HP", 570, 620);
        g.drawString(String.format("%d C's", restoreCost), 972, 605);


        g.setColor(Colors.MED_SEA_GREEN);
        g.drawString(String.format("Points Available to Spend: %d", player.getScore()), 580, 700);
        g.drawString("<SPACE> - Return to Game", 594, 740);
    }
}
