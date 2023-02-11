package Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float HBox;
    public entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;


    }
    protected void drawHBox(Graphics g){
        //debugging HitBox
        g.setColor(Color.MAGENTA);
        g.drawRect((int) HBox.x, (int) HBox.y, (int) HBox.width, (int) HBox.height);

    }

    protected void initHBox(float x ,float y, int width, int height) {
        HBox = new Rectangle2D.Float(x,y,width,height);
    }
    // protected void updateHBox(){
   //     HBox.x= (int) x;
 //       HBox.y= (int) y;
  //  }
    public Rectangle2D.Float getHBox(){
        return HBox;
    }
}
