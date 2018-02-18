package com.kelvin101.game.states;

import com.kelvin101.game.Game;
import com.kelvin101.game.display.Colors;
import com.kelvin101.game.input.KeyManager;

import java.awt.*;

public class PausedState extends State
{
    private KeyManager k;
    public PausedState(Game game)
    {
        super(game);
        k = game.getKeyManager();
    }

    public void tick()
    {
        if (k.p)
        {
            k.p = false;
            State.setState(game.gameState);
        }
        if (k.esc)
        {
            k.esc = false;
            State.setState(game.menuState);
        }
    }

    public void render(Graphics2D g)
    {
        Font zBold = new Font("Zorque", Font.BOLD, 80);
        Font jetItalic = new Font("Jet Set", Font.ITALIC, 30);
        g.setFont(zBold);

        g.setColor(Colors.LIGHT_RED);
        g.drawString("PAUSED", (game.getWidth() / 2) - 180, (game.getHeight() / 2) + 10);

        g.setFont(jetItalic);
        g.setColor(Colors.SALMON);
        g.drawString("<P> - Return to Game", (game.getWidth() / 2) - 375, (game.getHeight() / 2) + 70);
        g.drawString("<ESC> - Exit to Main Menu", (game.getWidth() / 2), (game.getHeight() / 2) + 70);
    }
}
