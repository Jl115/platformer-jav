package UI;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utils.constant.UI.PauseMenuB.*;
public class PauseMenuB extends PauseButtons {
    private  int rowIndex  , index;
    private boolean MouseOver, MousePressed;
    private BufferedImage[] img;
    public PauseMenuB(int x, int y, int width, int height ,int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImg();
    }

    private void loadImg() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PauseMenuB);
        img = new BufferedImage[3];

        for (int i = 0; i < img.length; i++)
            img[i] = temp.getSubimage(i*PmB_DefaultSize,rowIndex* PmB_DefaultSize,PmB_DefaultSize,PmB_DefaultSize);
    }

    public void update(){
        index = 0;
        if (MouseOver)
            index = 1;
        if (MousePressed)
            index=2;
    }
    public void draw(Graphics g){
        g.drawImage(img[index],x,y,PmB_Size,PmB_Size, null );
    }
    public void ResetBool(){
        MouseOver = false;
        MousePressed = false;
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

    public void resetBools() {
    }
}
