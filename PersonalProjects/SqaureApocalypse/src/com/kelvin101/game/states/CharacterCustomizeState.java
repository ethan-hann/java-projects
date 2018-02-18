package com.kelvin101.game.states;

import com.kelvin101.game.Game;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.entities.Player;
import com.kelvin101.game.input.MouseManager;

import javax.swing.*;
import java.awt.*;

public class CharacterCustomizeState extends State
{
    private MouseManager m;

    public CharacterCustomizeState(Game game)
    {
        super(game);
        m = game.getMouseManager();
    }

    @Override
    public void tick()
    {
        float x = m.getMouseX();
        float y = m.getMouseY();

        if (m.isLeftPressed())
        {
            if (isMouseClicked(x,y, 100, 200, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.DARK_BLUE;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 100, 400, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.BROWN;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 100, 600, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.LIGHT_PINK;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 600, 200, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.PINK;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 600, 400, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.SLATE_GREY;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 600, 600, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.WHITE;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 1100, 200, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.PEACH_PUFF;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 1100, 400, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.PERU;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x,y, 1100, 600, 128, 128))
            {
                MouseManager.leftPressed = false;
                Player.charColor = Colors.FOREST_GREEN;
                State.setState(game.menuState);
            }
            if (isMouseClicked(x, y, game.getWidth() / 2 - 200, game.getHeight() - 100, 400, 64))
            {
                MouseManager.leftPressed = false;
                Player.charColor = JColorChooser.showDialog(null, "Select Custom Color", Color.RED);
                State.setState(game.menuState);
            }
        }
        MenuState.menuHandler.tick();
    }

    @Override
    public void render(Graphics2D g)
    {
        Font jet = new Font("Jet Set", Font.PLAIN, 40);
        Font jetItalic = new Font("Jet Set", Font.ITALIC, 30);
        Font zBold = new Font("Zorque", Font.BOLD, 60);

        g.setFont(zBold);
        g.setColor(Colors.BLACK);
        g.drawString("Character Customization", (game.getWidth() / 2) - 455, 147);

        g.setColor(Colors.LIGHT_RED);
        g.drawString("Character Customization", (game.getWidth() / 2) - 450, 142);

        g.setColor(Colors.RED);
        g.drawString("Character Customization", (game.getWidth() / 2) - 445, 137);

        //Section 1
        g.setFont(jet);
        g.setColor(Colors.DARK_BLUE);
        g.fillOval(100, 200, 128, 128);
        g.drawString("Dark Blue", 248, 278);

        g.setColor(Colors.BROWN);
        g.fillOval(100, 400, 128, 128);
        g.drawString("Brown", 248, 484);

        g.setColor(Colors.LIGHT_PINK);
        g.fillOval(100, 600, 128, 128);
        g.drawString("Light Pink", 248, 690);

        //Section 2
        g.setColor(Colors.PINK);
        g.fillOval(600, 200, 128, 128);
        g.drawString("Pink", 750, 278);

        g.setColor(Colors.SLATE_GREY);
        g.fillOval(600, 400, 128, 128);
        g.drawString("Slate Grey", 750, 484);

        g.setColor(Colors.WHITE);
        g.fillOval(600, 600, 128, 128);
        g.drawString("White", 750, 690);

        //Section 3
        g.setColor(Colors.PEACH_PUFF);
        g.fillOval(1100, 200, 128, 128);
        g.drawString("Peach Puff", 1256, 278);

        g.setColor(Colors.PERU);
        g.fillOval(1100, 400, 128, 128);
        g.drawString("Peru", 1256, 484);

        g.setColor(Colors.FOREST_GREEN);
        g.fillOval(1100, 600, 128, 128);
        g.drawString("Forest Green", 1256, 690);

        //Select Custom Color
        g.setStroke(new BasicStroke(5f));
        g.setColor(Colors.MED_SEA_GREEN);
        g.drawRoundRect(game.getWidth() / 2 - 200, game.getHeight() - 100, 400, 64, 32, 32);

        g.setColor(Colors.SALMON);
        g.setFont(jetItalic);
        g.drawString("Custom Color", game.getWidth() / 2 - 105, game.getHeight() - 56);
    }
}
