package gamestates;

import UI.MenuBut;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends States implements Statemethods{

    private MenuBut[] buttons = new MenuBut[3];
    private BufferedImage BackgroundImg, BImg;
    private  int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        BImg= LoadSave.GetSpriteAtlas(LoadSave.MenuBimg);
    }

    private void loadBackground() {
        BackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MenuBackground);
        menuWidth= (int)(BackgroundImg.getWidth()* Game.Scale);
        menuHeight= (int)(BackgroundImg.getHeight()* Game.Scale);
        menuX = Game.GameWidth / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.Scale);
    }

    private void loadButtons() {
        buttons[0] = new MenuBut(Game.GameWidth /2 ,(int)( 150*Game.Scale), 0, GameStates.Playing);
        buttons[1] = new MenuBut(Game.GameWidth /2 ,(int)( 220*Game.Scale), 1, GameStates.Options);
        buttons[2] = new MenuBut(Game.GameWidth /2 ,(int)( 290*Game.Scale), 2, GameStates.Quit);
    }

    @Override
    public void update() {
        for ( MenuBut mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(BImg, 0, 0, Game.GameWidth, Game.GameHeight, null);
        g.drawImage(BackgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        for ( MenuBut mb : buttons)
            mb.draw(g);
    }

    @Override
    public void MouseClicked(MouseEvent e) {

    }

    @Override
    public void MousePressed(MouseEvent e) {
        for ( MenuBut mb : buttons){
            if (isIn(e, mb)){
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void MouseReleased(MouseEvent e) {
        for ( MenuBut mb : buttons){
            if (isIn(e, mb)){
                if(mb.isMousePressed())
                    mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for ( MenuBut mb : buttons){
            mb.resetBools();
        }
    }

    @Override
    public void MouseMoved(MouseEvent e) {
        for ( MenuBut mb : buttons)
            mb.setMouseOver(false);

        for ( MenuBut mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            GameStates.state = GameStates.Playing;
    }

    @Override
    public void KeyReleased(KeyEvent e) {

    }
}
