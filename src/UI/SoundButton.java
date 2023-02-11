package UI;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utils.constant.UI.PauseButtons.*;

public class SoundButton extends PauseButtons{
    private BufferedImage [][] SoundImg;
    private boolean MouseOver,MousePressed,Muted;
    private int rowIndex,columnIndex;
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadSoundImg();
    }

    private void loadSoundImg() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SoundButton);
        SoundImg = new BufferedImage[2][3];
        for (int j = 0; j<SoundImg.length; j ++)
            for (int i=0; i<SoundImg[j].length; i++)
                SoundImg[j][i] = temp.getSubimage(i*SoundSizedDefault,j*SoundSizedDefault,SoundSizedDefault,SoundSizedDefault);
    }
    public void update(){
        if (Muted)
            rowIndex = 1;
        else
            rowIndex = 0;

        columnIndex = 0;
        if (MouseOver)
            columnIndex= 1;
        if (MousePressed)
            columnIndex= 2;
    }

    public void resetBools(){
        MouseOver = false;
        MousePressed = false;
    }
    public void draw(Graphics g){
        g.drawImage(SoundImg[rowIndex][columnIndex],x ,y, width, height, null);
    }

    public boolean isMouseOver() {
        return MouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        MouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return MousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        MousePressed = mousePressed;
    }

    public boolean isMuted() {
        return Muted;
    }

    public void setMuted(boolean muted) {
        Muted = muted;
    }
}
