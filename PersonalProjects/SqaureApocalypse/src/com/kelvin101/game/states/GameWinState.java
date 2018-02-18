package com.kelvin101.game.states;

import com.kelvin101.game.Game;
import com.kelvin101.game.Handler;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.entities.Player;
import com.kelvin101.game.input.MouseManager;

import java.awt.*;

public class GameWinState extends State
{
    private Game game;
    private Player player;
    private Handler handler;
    private MouseManager m;

    Font jet = new Font("Jet Set", Font.PLAIN, 40);
    Font jetItalic = new Font("Jet Set", Font.ITALIC, 30);
    Font zBold = new Font("Zorque", Font.BOLD, 80);

    public GameWinState(Game game, Player player, Handler handler)
    {
        super(game);
        this.game = game;
        this.player = player;
        this.handler = handler;
        m = game.getMouseManager();
    }

    @Override
    public void tick()
    {
        float x = m.getMouseX();
        float y = m.getMouseY();

        if (game.getMouseManager().isLeftPressed())
        {
            //Main Menu
            if (isMouseClicked(x, y, 380, 674, 400, 64))
            {
                handler.clear();
                Game.audio.stopMusic("victory");
                Game.audio.playMusic("menu").loop();
                State.setState(game.menuState);
            }

            //Quit
            if (isMouseClicked(x, y, 800, 674, 400, 64))
            {
                System.exit(0);
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setFont(zBold);
        g.setColor(Colors.BLACK);
        g.drawString("YOU WIN!", 530, 138);

        g.setColor(Colors.LIGHT_RED);
        g.drawString("YOU WIN!", 525, 133);

        g.setColor(Colors.RED);
        g.drawString("YOU WIN!", 520, 128);

        g.setColor(Colors.SALMON);
        g.setFont(jet);
        g.drawString("You have SUCCESSFULLY rid the world of the unwanted squares!", 100, 280);

        g.setFont(jetItalic);
        g.drawString("Final Score: " + player.getScore(), 100, 325);
        g.drawString("Final Level: " + player.getLevel(), 100, 375);

        g.setColor(Colors.MED_SEA_GREEN);
        g.setStroke(new BasicStroke(5f));
        g.drawRoundRect(380, 674, 400, 64, 32, 32); //bottom rect

        g.setColor(Colors.SALMON);
        g.drawString("Main Menu", 500, 716); //bottom

        g.setColor(Colors.MED_SEA_GREEN);
        g.drawRoundRect(800, 674, 400, 64, 32, 32); //bottom rect

        g.setColor(Colors.SALMON);
        g.drawString("Quit", 960, 716); //bottom
    }
}
