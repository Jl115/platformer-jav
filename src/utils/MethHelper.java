package utils;

import main.Game;

import java.awt.geom.Rectangle2D;

public class MethHelper {

    public static boolean CanMoveH(float x, float y, float width, float height, int[][] lvlData){
        if(!IsSolid(x,y,lvlData)) {
            if (!IsSolid(x + width, y + height, lvlData)) {
                if (!IsSolid(x + width, y, lvlData)) {
                    if (!IsSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private  static boolean IsSolid(float x,  float y, int [][] lvlData){
        int maxWidth = lvlData[0].length*Game.TilesSize;
        int maxHeight = lvlData[0].length*Game.TilesSize;
        if(x <0 || x>= maxWidth){
            return true;
        }
        if(y < 0 || y>= maxHeight){
        //if(y < 0 || y>= Game.GameHeight){
            return true;
        }
        float xIndex = x / Game.TilesSize;
        float yIndex = y / Game.TilesSize;

        int value = lvlData[(int)yIndex][(int) xIndex];

        if(value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }
    public static float GetEntXPosNextWall(Rectangle2D.Float HBox, float xSpeed) {
        int currentTile = (int)(HBox.x / Game.TilesSize);
        if (xSpeed > 0){
            //right
            int tileXPos = currentTile*Game.TilesSize;
            int xOffset = (int)(Game.TilesSize-HBox.width);
            return tileXPos + xOffset -1;
        }else{
            //left
            return currentTile*Game.TilesSize;

        }
    }
    public static float GetEntityUnderAboveFloor(Rectangle2D.Float Hbox, float AirS){
        int currentTile = (int)(Hbox.y / Game.TilesSize);
        if(AirS > 0){
            //Falling
            int tileYPos = currentTile*Game.TilesSize;
            int yOffset = (int)(Game.TilesSize-Hbox.height);
            return tileYPos + yOffset -1;
        }else {
            //Jumping
            return currentTile*Game.TilesSize;
        }
    }
    public static boolean IsEntityOnFloor(Rectangle2D.Float Hbox, int[][] lvlData) {
        //check Pixel below bottomRight / Left
        if (!IsSolid(Hbox.x, Hbox.y + Hbox.height + 1, lvlData))
            if (!IsSolid(Hbox.x, Hbox.y + Hbox.width + 1, lvlData))
                return false;
        return true;
    }
}
