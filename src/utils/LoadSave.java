package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String Player_Atlas = "player_sprites.png";
    public static final String Lvl_Atlas = "outside_sprites.png";
    //public static final String Lvl_One = "level_one_data.png";
    public static final String Lvl_One = "level_one_data_long_HW.png";
    public static final String MenuB= "button_atlas.png";
    public static final String MenuBackground= "menu_background.png";
    public static final String PauseBackground= "pause_menu.png";
    public static final String PauseMenuB= "urm_buttons.png";
    public static final String VolumeSlider= "volume_buttons.png";
    public static final String SoundButton= "sound_button.png";
    public static final String MenuBimg= "background_menu.jpg";

    public static BufferedImage GetSpriteAtlas(String filename) {

        BufferedImage img = null;

        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);

        try {
            img = ImageIO.read(is);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static int [] [] GetLvlD() {

        BufferedImage img = GetSpriteAtlas(Lvl_One);
        int[][] lvlD = new int[Game.GameHeight][Game.GameWidth];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value>= 48){
                    value =0;
                }
                lvlD[j][i] = value;
            }

        }
        return lvlD;
    }
}
