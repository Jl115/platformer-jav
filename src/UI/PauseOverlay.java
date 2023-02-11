package UI;

import gamestates.GameStates;
import gamestates.Playing;
import main.Game;
import main.MainClass;
import utils.LoadSave;
import utils.constant;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static utils.constant.UI.PauseButtons.*;
import static utils.constant.UI.PauseMenuB.*;
import static utils.constant.UI.VolumeB.*;

public class PauseOverlay {
    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton MusicButton, sfxButton;
    private PauseMenuB MenuB, ReplayB, UnpausedB;
    private VolumeB volumeB;
    public PauseOverlay(Playing playing){
        this.playing =playing;
        loadBackground();
        CreateSoundButtons();
        CreatePmB();
        CreateVolumeB();
    }

    private void CreateVolumeB() {
        int vX=(int)(309*Game.Scale);
        int vY=(int)(278 * Game.Scale);
        volumeB= new VolumeB(vX,vY,Slider_W,Volume_H);
    }

    private void CreatePmB() {
        int MenuX = (int)(313*Game.Scale);
        int ReplayX = (int)(387*Game.Scale);
        int UnpausedX = (int)(462*Game.Scale);
        int b_Y = (int)(325*Game.Scale);
        MenuB = new PauseMenuB(MenuX,b_Y,PmB_Size,PmB_Size,2);
        ReplayB = new PauseMenuB(ReplayX,b_Y,PmB_Size,PmB_Size,1);
        UnpausedB = new PauseMenuB(UnpausedX,b_Y,PmB_Size,PmB_Size,0);
    }

    private void CreateSoundButtons() {
        int SoundX = (int)(450*Game.Scale);
        int MusicY = (int)(140*Game.Scale);
        int SfxY = (int)(186*Game.Scale);
        MusicButton = new SoundButton(SoundX,MusicY,SoundSized,SoundSized);
        sfxButton = new SoundButton(SoundX,SfxY,SoundSized,SoundSized);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PauseBackground);
        bgW = (int) (backgroundImg.getWidth()* Game.Scale);
        bgH = (int) (backgroundImg.getHeight()* Game.Scale);
        bgX = Game.GameWidth/2-bgW/2;
        bgY = (int)(25*Game.Scale);
    }

    public void draw(Graphics g){
        // Background
        g.drawImage(backgroundImg, bgX,bgY,bgW,bgH, null);
        //sound Buttons
        MusicButton.draw(g);
        sfxButton.draw(g);

        //Menu Buttons
        MenuB.draw(g);
        ReplayB.draw(g);
        UnpausedB.draw(g);

        //VolumeButton/Slider//
        volumeB.draw(g);
    }
    public void update(){
        MusicButton.update();
        sfxButton.update();
        MenuB.update();
        ReplayB.update();
        UnpausedB.update();
        volumeB.update();

    }

    public void MouseDragged(MouseEvent e){
        if (volumeB.isMousePressed()){
            volumeB.changeX(e.getX());
        }
    }



    public void MousePressed(MouseEvent e) {
        if (isIn(e, MusicButton))
            MusicButton.setMousePressed(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (isIn(e, MenuB))
            MenuB.setMousePressed(true);
        else if (isIn(e, ReplayB))
            MenuB.setMousePressed(true);
        else if (isIn(e, UnpausedB))
            MenuB.setMousePressed(true);
        else if (isIn(e, volumeB))
            volumeB.setMousePressed(true);
    }


    public void MouseReleased(MouseEvent e) {
        if (isIn(e, MusicButton)){
            if (MusicButton.isMousePressed())
                MusicButton.setMuted(!MusicButton.isMuted());
        }else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
        }else if (isIn(e, MenuB)) {
            if (MenuB.isMousePressed()) {
                GameStates.state = GameStates.Menu;
                playing.UnpauseGame();
            }

        }else if (isIn(e, ReplayB)) {
            if (ReplayB.isMousePressed())
                System.out.print("replay");
        }else if (isIn(e, UnpausedB)) {
            if (UnpausedB.isMousePressed())
                System.out.print("paused");
            playing.UnpauseGame();
        }
        MusicButton.resetBools();
        sfxButton.resetBools();
        MenuB.resetBools();
        ReplayB.resetBools();
        UnpausedB.resetBools();
        volumeB.resetBools();
    }


    public void MouseMoved(MouseEvent e) {
        MusicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        MenuB.setMouseOver(false);
        ReplayB.setMouseOver(false);
        UnpausedB.setMouseOver(false);
        volumeB.setMouseOver(false);

        if (isIn(e, MusicButton))
            MusicButton.setMouseOver(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (isIn(e, MenuB))
            MenuB.setMouseOver(true);
        else if (isIn(e, ReplayB))
            ReplayB.setMouseOver(true);
        else if (isIn(e, UnpausedB))
            UnpausedB.setMouseOver(true);
        else if (isIn(e, volumeB))
            volumeB.setMouseOver(true);
    }
    private boolean isIn(MouseEvent e, PauseButtons b){
        return b.getBounds().contains(e.getX(), e.getY());

    }


}
