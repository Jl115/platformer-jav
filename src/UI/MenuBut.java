package UI;

import gamestates.GameStates;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

import static utils.constant.UI.Buttons.*;

public class MenuBut {
private int xPos, yPos, rowIndex, index;
private int xOffsetCenter = B_Width / 2;
private GameStates states;
private BufferedImage [] imgs;
private boolean mouseOver, mousePressed;

private Rectangle bounds;
    public MenuBut(int xPos, int yPos, int rowIndex, GameStates states){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.states = states;
        loadImg();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos -xOffsetCenter, yPos,B_Width, B_Height);

    }

    private void loadImg() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MenuB);
        for( int i = 0; i< imgs.length; i++)
          imgs[i] =  temp.getSubimage(i*B_Width_D,rowIndex*B_Height_D,B_Width_D,B_Height_D);
    }
    public void draw(Graphics g){
        g.drawImage(imgs[index],xPos - xOffsetCenter, yPos, B_Width, B_Height, null );
    }
    public void update(){
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;

    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void applyGameState(){
        GameStates.state = states;
    }
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
