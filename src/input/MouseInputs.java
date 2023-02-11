package input;

import gamestates.GameStates;
import main.GamePanel;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public MouseInputs( GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameStates.state) {
            case Playing:
                gamePanel.getGame().getPlaying().MouseClicked(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStates.state) {
            case Menu:
                gamePanel.getGame().getMenu().MousePressed(e);
                break;
            case Playing:
                gamePanel.getGame().getPlaying().MousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStates.state) {
            case Menu:
                gamePanel.getGame().getMenu().MouseReleased(e);
                break;
            case Playing:
                gamePanel.getGame().getPlaying().MouseReleased(e);
                break;
            default:
                break;
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
        switch (GameStates.state) {
            case Playing:
                gamePanel.getGame().getPlaying().MouseDragged(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        switch (GameStates.state) {
            case Menu:
                gamePanel.getGame().getMenu().MouseMoved(e);
                break;
            case Playing:
                gamePanel.getGame().getPlaying().MouseMoved(e);
                break;
            default:
                break;
        }

    }
}
