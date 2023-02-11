package gamestates;

import Entity.Player;
import UI.PauseOverlay;
import levels.LvlMang;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Playing extends States implements Statemethods {
    private LvlMang lvlMang;
    private Player player ;
    private boolean poused = false;


    /* Width calculation*/
    private int xLvlOffset;
    private int LeftBorder = (int) (0.3*Game.GameWidth);
    private int RightBorder = (int) (0.7*Game.GameWidth);
    private int lvlTilesWide = LoadSave.GetLvlD()[0].length;
    private int MaxTileOffset = lvlTilesWide-Game.TilesWidth;
    private int MaxLvlOffsetX = MaxTileOffset*Game.TilesSize;
    /* Height calculation
    * / \Right
    *  |
    *  |
    *  |
    * / \ Left
    *  */
    private int yLvlOffset;
    private int LeftBorderHeight  = (int) (0.1*Game.GameHeight);
    private int RightBorderHeight = (int) (0.9*Game.GameHeight);
    private int lvlTilesHeight = LoadSave.GetLvlD()[0].length;
    private int MaxTileOffsetHeight = lvlTilesHeight-Game.GameHeight;
    private int MaxLvlOffsetY = MaxTileOffsetHeight*Game.TilesSize;
    private PauseOverlay pauseOverlay;

    public Playing(Game game) {
        super(game);
        initClasses();
    }


    private void initClasses() {
        lvlMang = new LvlMang(game);
        player = new Player(200, 200, (int) (64*Game.Scale), (int) (40*Game.Scale));
        player.loadLvlD(lvlMang.LvlGetLvl().getLvlDat());
        pauseOverlay = new PauseOverlay(this);
    }




    @Override
    public void update() {
    if (!poused){
        LvlMang.update();
        player.update();
        CheckCloseBorder();
    }else{
        pauseOverlay.update();
    }



    }

    private void CheckCloseBorder() {
        int PlayerX = (int) player.getHBox().x;
        int PlayerY = (int) player.getHBox().y;
        int DiffX = PlayerX-xLvlOffset;
        int DiffY = PlayerY-yLvlOffset;

        //check X
        if (DiffX > RightBorder)
            xLvlOffset += DiffX-RightBorder;
        else if (DiffX < LeftBorder)
            xLvlOffset += DiffX-LeftBorder;

        //check Y
        if (DiffY > RightBorderHeight)
            yLvlOffset += DiffY-RightBorderHeight;
        else if (DiffY < LeftBorderHeight)
            yLvlOffset += DiffY-LeftBorderHeight;

        if(xLvlOffset > MaxLvlOffsetX)
            xLvlOffset = MaxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;

        if(yLvlOffset > MaxLvlOffsetY)
            yLvlOffset = MaxLvlOffsetY;
        else if (yLvlOffset < 0)
            yLvlOffset = 0;
    }

    @Override
    public void draw(Graphics g) {
        lvlMang.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
        if(poused)
        pauseOverlay.draw(g);
    }

    @Override
    public void MouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            player.setAttack(true);
        }
    }

    @Override
    public void MousePressed(MouseEvent e) {
        if(poused)
            pauseOverlay.MousePressed(e);

    }

    @Override
    public void MouseReleased(MouseEvent e) {
        if(poused)
            pauseOverlay.MouseReleased(e);
    }

    @Override
    public void MouseMoved(MouseEvent e) {
        if(poused)
            pauseOverlay.MouseMoved(e);
    }
    public void MouseDragged(MouseEvent e){
        if (poused)
            pauseOverlay.MouseDragged(e);
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                GameStates.state = GameStates.Menu;
                break;
            case KeyEvent.VK_ENTER:
                poused = !poused;
                break;
        }
    }

    @Override
    public void KeyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;

        }
    }
    public void UnpauseGame(){
        poused = false;
    }
    public void windowFocusLost() {
        player.resetDirBool();
    }
    public Player getPlayer(){
        return player;
    }
}
