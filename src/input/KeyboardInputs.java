package input;

import gamestates.GameStates;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.constant.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (GameStates.state) {
            case Playing:
                gamePanel.getGame().getPlaying().KeyPressed(e);
                break;
            case Menu:
                gamePanel.getGame().getMenu().KeyPressed(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameStates.state) {
            case Playing:
                gamePanel.getGame().getPlaying().KeyReleased(e);
                break;
            case Menu:
                gamePanel.getGame().getMenu().KeyReleased(e);
                break;
            default:
                break;
        }

    }
}