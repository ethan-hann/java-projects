package com.kelvin101.game.states;

import com.kelvin101.game.Game;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.input.MouseManager;

import java.awt.*;

public class HelpState extends State
{
    public HelpState(Game game)
    {
        super(game);
    }

    public void tick()
    {
        float x = game.getMouseManager().getMouseX();
        float y = game.getMouseManager().getMouseY();

        if (game.getMouseManager().isLeftPressed())
        {
            if (isMouseClicked(x, y, 590, 730, 400, 64))
            {
                MouseManager.leftPressed = false;
                State.setState(game.menuState);
            }
        }
    }

    public void render(Graphics2D g)
    {
        Font jet = new Font("Jet Set", Font.PLAIN, 22);
        Font jetItalic = new Font("Jet Set", Font.ITALIC, 30);
        Font zBold = new Font("Zorque", Font.BOLD, 80);
        g.setFont(zBold);

        g.setColor(Colors.BLACK);
        g.drawString("Help", 680, 94);

        g.setColor(Colors.LIGHT_RED);
        g.drawString("Help", 685, 89);

        g.setColor(Colors.RED);
        g.drawString("Help", 690, 84);

        g.setFont(jet);
        g.setColor(Colors.SALMON);
        g.drawString("You are a circle in a world overrun by squares. You are the only circle left.", 144, 140);
        g.drawString("-> Objective: to avoid the various squared enemies that will be trying to kill you.", 144, 190);
        g.drawString("-> Use W-A-S-D to move the circle around the screen. At Level 50: Use NUMPAD5 to shoot bullets.", 144, 240);
        g.drawString("-> The levels will increase in difficulty; starting easy and getting very, very hard.", 144, 290);
        g.drawString("-> Once you reach level 50, you will have to kill the Square Boss using bullets in order to win the game.", 144, 340);

        g.setColor(Colors.RED);
        g.fillRect(144, 390, 32, 32);
        g.drawString(" - Basic Enemy: nothing special, moves relatively slow; if touched, will deduct 1 HP", 186, 411);

        g.setColor(Colors.MED_SEA_GREEN);
        g.fillRect(144, 440, 32, 32);
        g.drawString(" - Fast Enemy: moves very fast up and down; if touched, will deduct 2 HP", 186, 462);

        g.setColor(Colors.DARK_SLATE_BLUE);
        g.fillRect(144, 490, 32, 32);
        g.drawString(" - Smart Enemy: Follows you around, forcing you to move and not stay still; if touched, will deduct 3 HP", 186, 512);

        g.setColor(Colors.TOMATO);
        g.fillRect(144, 540, 32, 32);
        g.drawString(" - Hard Enemy: will change speed when it hits a wall, very unpredictable; if touched, will deduct 4 HP", 186, 562);

        g.setColor(Colors.KHAKI);
        g.fillRect(144, 590, 32, 32);
        g.drawString(" - Boss: if touched, will deduct 1000 HP (in other words, instant death)", 186, 612);
        g.drawString(" - Also shoots at you, which you have to avoid. Each bolt will deduct 2 HP", 186, 642);


        g.setFont(jetItalic);
        g.setColor(Colors.MED_SEA_GREEN);
        g.drawString("How Long Will You Last? . . .", (game.getWidth() / 2) - 220, 715);

        g.setFont(jetItalic);
        g.setStroke(new BasicStroke(5f));
        g.drawRoundRect(590, 730, 400, 64, 32, 32); //bottom rect
        g.drawString("Back", 750, 770); //bottom
    }
}
