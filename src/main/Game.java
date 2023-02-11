package main;
import gamestates.GameStates;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 144;
    private final int UPS_SET = 200;
    private Playing playing;
    private Menu menu;
    public final static int TilesDefaultSize = 32;
    //round number for float !!
    public final static float Scale = 1.5f;
    public final static int TilesHeight = 14; //visible tiles!!
    public final static int TilesWidth = 26; //visible tiles!!
    public final static int TilesSize = (int) (TilesDefaultSize * Scale);
    public final static int GameWidth = TilesSize * TilesWidth;
    public final static int GameHeight = TilesSize * TilesHeight;


    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();


        //needs to be last !
        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (GameStates.state) {
            case Playing:
                playing.update();
                break;
            case Menu:
                menu.update();
                break;
            default:
                break;
        }
    }

    public void render(Graphics g) {
        switch (GameStates.state) {
            case Playing:
                playing.draw(g);
                break;
            case Menu:
                menu.draw(g);
                break;
            case Options:
            case Quit:
            default:
                System.exit(0);
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        int ups = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();

                frames++;
                deltaF--;
            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS:" + frames + " | UPS:" + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        if (GameStates.state == GameStates.Playing)
            playing.getPlayer().resetDirBool();
    }
    public Menu getMenu(){
        return menu;
    }
    public Playing getPlaying(){
        return playing;
    }
}
