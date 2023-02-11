package main;

import input.KeyboardInputs;
import input.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static main.Game.GameHeight;
import static main.Game.GameWidth;
import static utils.constant.PlayerConstants.*;
import static utils.constant.Directions.*;



public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {

        mouseInputs = new MouseInputs(this);
        this.game = game;
        setGamePanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setGamePanelSize() {
        Dimension size = new Dimension(GameWidth, GameHeight);
        setPreferredSize(size);
        System.out.println("y:"+GameHeight+  "__x:"+GameWidth);
    }


    public void updateG() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);




    }

    public Game getGame() {
        return game;
    }


}