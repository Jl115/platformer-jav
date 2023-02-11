package levels;

import main.Game;
import utils.LoadSave;
import utils.MethHelper;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TilesHeight;
import static main.Game.TilesSize;

public class LvlMang {

    private Game game;
    private BufferedImage [] levelSpites;
    private Level levelOne;
    public LvlMang(Game game){
        this.game = game;
        importOutsideSprite();
        levelOne = new Level(LoadSave.GetLvlD());
    }

    private void importOutsideSprite() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Lvl_Atlas);
        levelSpites = new BufferedImage[48];
        for(int j = 0; j < 4; j++){             // J = Y axis
            for (int i = 0; i <12; i++ ){       // i = X axis
                int index = j*12 + i;
                levelSpites[index] = img.getSubimage(i*32, j*32, 32,32);
            }
        }

    }


    public void draw(Graphics g, int xLvlOffset, int yLvlOffset){
        //for (int j = 0; j< levelOne.getLvlDat().length; j ++)

        for (int j = 0; j< TilesSize; j ++)
            for(int i = 0; i < levelOne.getLvlDat().length; i ++){
                int index = levelOne.getSpriteI(i, j);
                g.drawImage(levelSpites[index], TilesSize* i-xLvlOffset, TilesSize*j-yLvlOffset, TilesSize, TilesSize, null);
            }
    }


    public static void update() {

    }

    public Level LvlGetLvl(){
        return levelOne;
    }
}
