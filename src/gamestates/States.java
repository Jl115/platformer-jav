package gamestates;

import UI.MenuBut;
import main.Game;

import java.awt.event.MouseEvent;

public class States {
    protected Game game;
    public States(Game game){
        this.game = game;
    }
    public boolean isIn(MouseEvent e, MenuBut mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }
    public Game getGame() {
        return game;
    }
}
