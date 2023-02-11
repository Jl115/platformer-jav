package UI;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.constant.UI.VolumeB.*;

public class VolumeB extends PauseButtons {
private  BufferedImage[] img;
private  BufferedImage slider;
private  int index=0;
private  int buttonX,minX,maxX;
private  boolean MouseOver;
    private  boolean MousePressed;


    public VolumeB(int x, int y, int width, int height) {
        super(x+width/2, y, Volume_W, height);
        bounds.x-=Volume_W/2;
        buttonX=x+width/2;
        this.x=x;
        this.width= width;
        minX=x+Volume_W/2;
        maxX=x+width-Volume_W/2;
        loadImg();
    }
    private void loadImg() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VolumeSlider);
        img = new BufferedImage[3];
        for(int i=0;i<img.length;i++)
            img[i]= temp.getSubimage(i*Volume_Default_Width,0, Volume_Default_Width,Volume_Default_Height);
        slider = temp.getSubimage(3*Volume_Default_Width, 0, Slider_Default_Width, Volume_Default_Height);
    }

    public  void update(){
        index = 0;
        if (MouseOver)
            index = 1;
        if (MousePressed)
            index=2;
    }
    public  void draw(Graphics g){
        g.drawImage(slider,x,y,width,height,null);
        g.drawImage(img[index],buttonX-Volume_W/2,y,Volume_W,height,null);
    }
    public void changeX(int x){
        if (x<minX)
            buttonX =minX;
        else if (x>maxX)
            buttonX=maxX;
        else
            buttonX=x;

        bounds.x = buttonX-Volume_W/2;
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
